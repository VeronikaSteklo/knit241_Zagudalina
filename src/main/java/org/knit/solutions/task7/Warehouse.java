package org.knit.solutions.task7;

import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private final int CAPACITY = 5;
    private final Queue<String> productsQueue = new LinkedList<>();

    public synchronized void addProduct(String productName) {
        while (productsQueue.size() == CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        productsQueue.add(productName);
        System.out.println("Товар " + productName + " был добавлен на склад.");
        notify();
    }

    public synchronized void buyProduct() {
        while (productsQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Товар " + productsQueue.poll() + " был взят со склада.");
        notify();
    }
}
