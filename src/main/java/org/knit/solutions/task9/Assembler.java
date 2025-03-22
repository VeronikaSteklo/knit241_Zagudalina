package org.knit.solutions.task9;

import java.util.concurrent.BlockingQueue;

public class Assembler implements Runnable {
    private final BlockingQueue<Detail> stampingQueue;
    private final BlockingQueue<Detail> reassemblingQueue;
    private final BlockingQueue<Detail> assemblingQueue;
    private final Factory factory;

    public Assembler(
            BlockingQueue<Detail> stampingQueue,
            BlockingQueue<Detail> reassemblingQueue,
            BlockingQueue<Detail> assemblingQueue,
            Factory factory
    ) {
        this.stampingQueue = stampingQueue;
        this.reassemblingQueue = reassemblingQueue;
        this.assemblingQueue = assemblingQueue;
        this.factory = factory;
    }

    @Override
    public void run() {
        while (!factory.getWorkFinished()) {
            try {
                if (!stampingQueue.isEmpty()) {
                    Detail detail = stampingQueue.take();
                    Thread.sleep(1500);
                    assemblingQueue.put(detail);
                    System.out.println("Сборщик собрал деталь " + detail.getId());
                } else if (!reassemblingQueue.isEmpty()) {
                    Detail detail = reassemblingQueue.take();
                    Thread.sleep(1000);
                    assemblingQueue.put(detail);
                    System.out.println("Сборщик пересобрал деталь " + detail.getId());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Сборщик ушел домой.");
    }
}
