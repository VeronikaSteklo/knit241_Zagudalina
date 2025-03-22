package org.knit.solutions.semestr1.lab7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTreeWalker {
    private Path directory;

    public FileTreeWalker(String directory) {
        this.directory = Paths.get(directory);
    }

    public Path getDirectory() {
        return directory;
    }

    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    public void walkFileTreeAndCountWordsInTxts() {
        CounterFileVisitor counterFileVisitor = new CounterFileVisitor();
        try {
            Files.walkFileTree(directory, counterFileVisitor);
            System.out.println("Всего слов в текстовых файлах директории " + directory.toString() + ": " + counterFileVisitor.getTotalWordsCount());
        } catch (IOException e) {
            System.out.println("Ошибка при проходе каталога: " + e.getMessage());
        }
    }
}
