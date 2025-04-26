package org.knit.solutions.task20.security;

import lombok.Getter;
import lombok.Setter;
import org.knit.solutions.task20.repository.InMemoryPasswordRepository;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MasterPasswordHolder {
    private final InMemoryPasswordRepository repository;
    @Getter
    @Setter
    private String masterPassword;


    public MasterPasswordHolder(InMemoryPasswordRepository repository) {
        this.repository = repository;
        masterPassword = repository.getMasterPassword();
        if (masterPassword == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите мастер-пароль: ");
            String masterPassword = scanner.nextLine();
            setMasterPassword(masterPassword);
        }
    }

    public void clear() {
        if (masterPassword != null) {
            masterPassword = null;
        }
    }
}