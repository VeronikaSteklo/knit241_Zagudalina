package org.knit.solutions.task6;

public class Railway {
    private volatile boolean isClosed = false;

    public synchronized void trainIsPassing() {
        System.out.println("Поезд приближается, шлагбаум закрывается.");
        isClosed = true;
    }

    public synchronized void trainPassed() {
        System.out.println("Поезд отдаляется, шлагбаум открывается.");
        isClosed = false;
        notifyAll();
    }

    public synchronized void passCar(String carName) {
        while (isClosed) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(carName + " проехала переезд");
    }
}
