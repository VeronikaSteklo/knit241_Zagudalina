package org.knit.solutions.semestr1.lab11;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {
    private FileDAO fileDAO;

    public FileProcessor(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void processFiles(String stringPath) {
        Path path = Paths.get(stringPath);
        try {
            if (Files.isDirectory(path)) {
                File folder = new File(stringPath);
                for (var file : folder.listFiles()) {
                    if (file.length() < 10_485_760) {
                        fileDAO.saveFile(file);
                    } else {
                        System.out.println("Файл " + file.getName() + " слишком большой");
                    }
                }
            } else if (Files.isRegularFile(path)) {
                File file = new File(stringPath);
                if (file.length() < 10_485_760) {
                    fileDAO.saveFile(file);
                } else {
                    System.out.println("Файл " + file.getName() + " слишком большой");
                }
            } else {
                System.out.println("Путь кривой.");
            }
        }
        catch (Exception e) {
            System.out.println("Произошла фигня: " + e.getLocalizedMessage());
        }
    }
}
