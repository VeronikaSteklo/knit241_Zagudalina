package org.knit.solutions.task20.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knit.solutions.task20.crypto.EncryptionService;
import org.knit.solutions.task20.model.LoginPasswordDTO;
import org.knit.solutions.task20.model.PasswordDataWrapper;
import org.knit.solutions.task20.model.PasswordEntry;
import org.knit.solutions.task20.repository.PasswordRepository;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FileStorageService {

    private static final File FILE = new File(
            "C:\\\\Users\\\\veron\\\\IdeaProjects\\\\knit241_Zagudalina\\\\src\\\\main\\\\java\\\\org\\\\knit\\\\solutions\\\\task20\\\\files\\\\passwords.json"
    );

    private final PasswordRepository repository;
    private final EncryptionService encryptionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FileStorageService(PasswordRepository repository,
                              EncryptionService encryptionService) {
        this.repository = repository;
        this.encryptionService = encryptionService;
    }

    @EventListener(ContextClosedEvent.class)
    public void saveToFile() {
        try {
            List<PasswordEntry> entries = repository.findAll();

            Map<String, LoginPasswordDTO> map = entries.stream()
                    .collect(Collectors.toMap(
                            PasswordEntry::getSite,
                            entry -> new LoginPasswordDTO(entry.getLogin(), entry.getEncryptedPassword())
                    ));

            PasswordDataWrapper wrapper = new PasswordDataWrapper();
            wrapper.setMasterPassword(map);

            String json = objectMapper.writeValueAsString(wrapper);
            String encryptedJson = encryptionService.encrypt(json);
            Files.writeString(FILE.toPath(), encryptedJson);
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    @EventListener(ContextRefreshedEvent.class)
    public void loadFromFile() {
        try {
            if (FILE.exists() && FILE.length() > 0) {
                String encryptedJson = Files.readString(FILE.toPath());

                if (encryptedJson != null && !encryptedJson.trim().isEmpty()) {
                    String decryptedJson = encryptionService.decrypt(encryptedJson);

                    PasswordDataWrapper wrapper = objectMapper.readValue(decryptedJson, PasswordDataWrapper.class);

                    if (wrapper.getMasterPassword() != null) {
                        for (Map.Entry<String, LoginPasswordDTO> entry : wrapper.getMasterPassword().entrySet()) {
                            repository.save(new PasswordEntry(
                                    entry.getKey(),
                                    entry.getValue().getLogin(),
                                    entry.getValue().getPassword()
                            ));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }
}
