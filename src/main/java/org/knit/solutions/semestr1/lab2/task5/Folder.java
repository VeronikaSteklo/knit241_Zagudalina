package org.knit.solutions.semestr1.lab2.task5;


import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    String name;
    List<FileSystemComponent> files;

    Folder(String name) {
        this.name = name;
        files = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        double result = 0;
        for (FileSystemComponent file : files) {
            result += file.getSize();
        }
        return result;
    }

    public void add(FileSystemComponent component) {
        files.add(component);
    }

    public void remove(FileSystemComponent component) {
        files.remove(component);
    }

    public void display(String indent) {
        indent = indent.isEmpty() ? " " : indent;
        System.out.println(indent + getName() + " (" + getSize() + " bytes)");
        for (FileSystemComponent file: files) {
            file.display(indent + indent);
        }
    }
}
