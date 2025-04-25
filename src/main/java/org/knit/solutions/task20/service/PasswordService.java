package org.knit.solutions.task20.service;

import org.knit.solutions.task20.clipboard.ClipboardCleaner;
import org.knit.solutions.task20.clipboard.ClipboardService;
import org.knit.solutions.task20.crypto.EncryptionService;
import org.knit.solutions.task20.model.PasswordEntry;
import org.knit.solutions.task20.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    private final PasswordRepository repository;
    private final EncryptionService encryptionService;
    private final ClipboardService clipboardService;
    private final ClipboardCleaner clipboardCleaner;
    private final FileStorageService fileStorageService;

    @Autowired
    public PasswordService(PasswordRepository repository,
                           EncryptionService encryptionService,
                           ClipboardService clipboardService,
                           ClipboardCleaner clipboardCleaner,
                           FileStorageService fileStorageService) {
        this.repository = repository;
        this.encryptionService = encryptionService;
        this.clipboardService = clipboardService;
        this.clipboardCleaner = clipboardCleaner;
        this.fileStorageService = fileStorageService;
    }

    public void addPassword(String site, String login, String plainPassword) {
        String encryptedPassword = encryptionService.encrypt(plainPassword);
        PasswordEntry entry = new PasswordEntry(site, login, encryptedPassword);
        repository.save(entry);
        fileStorageService.saveToFile();
    }

    public void listPasswords() {
        List<PasswordEntry> all = repository.findAll();
        if (all.isEmpty()) {
            System.out.println("Нет сохранённых записей.");
        } else {
            for (PasswordEntry entry : all) {
                System.out.println("Сайт: " + entry.getSite() + ", Логин: " + entry.getLogin());
            }
        }
    }

    public void deletePassword(String site) {
        repository.deleteBySite(site);
        fileStorageService.saveToFile();
    }

    public void copyPasswordToClipboard(String site) {
        PasswordEntry entry = repository.findBySite(site);
        if (entry == null) {
            System.out.println("Запись не найдена.");
            return;
        }
        String decryptedPassword = encryptionService.decrypt(entry.getEncryptedPassword());
        clipboardService.copyToClipboard(decryptedPassword);
        clipboardCleaner.scheduleClipboardClear(10_000);
        System.out.println("Пароль скопирован в буфер обмена.");
    }
}