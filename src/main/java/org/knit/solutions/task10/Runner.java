package org.knit.solutions.task10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Runner {
    private final double PROBABILITY_OF_FUCKED = 0.1;
    private final int DISTANCE_TO_RUN = 10;

    private ThreadLocal<Integer> currentDistance = ThreadLocal.withInitial(() -> 0);
    AtomicBoolean allAlive;
    private final CyclicBarrier barrier;
    private final int number;

    public Runner(
            AtomicBoolean allAlive,
            CyclicBarrier barrier,
            int number
    ) {
        this.allAlive = allAlive;
        this.barrier = barrier;
        this.number = number;
    }

    public void prepareForStart() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 3000));
            System.out.println("Приготовился " + number + " бегун");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (allAlive.get() && currentDistance.get() < DISTANCE_TO_RUN) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (ThreadLocalRandom.current().nextDouble(0, 1) > PROBABILITY_OF_FUCKED) {
                currentDistance.set(currentDistance.get() + 1);
            } else {
                try {
                    System.out.println("Сломался " + number + " бегун");
                    allAlive.set(false);
                    barrier.await();
                } catch (BrokenBarrierException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            System.out.println(number + " бегун закончил бежать");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
