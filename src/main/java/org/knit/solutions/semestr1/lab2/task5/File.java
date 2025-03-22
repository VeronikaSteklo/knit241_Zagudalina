package org.knit.solutions.semestr1.lab2.task5;

public class File implements FileSystemComponent {
    String name;
    double size;

    File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public void add(FileSystemComponent component) {
        System.out.println("File не может хранить в себе другие файлы.");
    }

    public void remove(FileSystemComponent component) {
        System.out.println("File не хранит в себе другие файлы.");
    }

    public void display(String indent) {
        System.out.println(indent + getName() + " (" + getSize() + " bytes)");
    }
}
