package org.knit.solutions.semestr1.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CounterFileVisitor implements FileVisitor<Path> {
    private int totalWordsCount;

    public CounterFileVisitor() {
        totalWordsCount = 0;
    }

    public int getTotalWordsCount() {
        return totalWordsCount;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().endsWith(".txt")) {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                int fileWordsCount = 0;
                while ((line = reader.readLine()) != null) {
                    // Разбиваем строку на слова, используя пробелы и другие разделители
                    String[] words = line.split("\\s+");
                    fileWordsCount += words.length;
                }
                System.out.println("Cлов в файле " + file + ": " + fileWordsCount);
                totalWordsCount += fileWordsCount;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
