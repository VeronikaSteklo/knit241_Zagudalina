package org.knit.Solutions.Task10;

import java.util.concurrent.*;

/*
📌 Описание
Группа спортсменов участвует в забеге. Однако перед началом гонки все должны собраться на старте. Как только все участники будут готовы, забег начнётся одновременно. Используйте CyclicBarrier, чтобы синхронизировать запуск гонки.

Каждый бегун стартует одновременно, затем бежит разное время (симулируется Thread.sleep), после чего финиширует. Как только все бегуны завершат дистанцию, программа выводит сообщение о завершении гонки.

🎯 Требования к задаче:
Создать CyclicBarrier для синхронизации начала забега.
Реализовать класс Runner, который будет выполнять следующую логику в потоке:
Ожидание старта (использование barrier.await()).
Симуляция бега (Thread.sleep(randomTime)).
Вывод сообщения о финише.
После финиша всех участников программа должна сообщить, что гонка завершена.
Количество бегунов передаётся в аргументах командной строки или задаётся константой.
🔧 Подсказка
Используйте Executors.newFixedThreadPool() для управления потоками.
Для генерации случайного времени забега можно использовать ThreadLocalRandom.current().nextInt(500, 3000).
🔹 Дополнительное задание (по желанию):

Добавить ещё одну CyclicBarrier, чтобы дождаться всех бегунов на финише перед выводом финального сообщения.
Добавить возможность прерывания гонки (например, если один из бегунов "травмируется" и не может продолжить).
 */

import org.knit.Solutions.TasksDescripton;

@TasksDescripton(number = 10, name = "Задача 10: Гонка бегунов с использованием CyclicBarrier")

public class RunnerRace {
    public static void main(String[] args) {
        final int NUM_RUNNERS = 10; // Количество бегунов
        CyclicBarrier startBarrier = new CyclicBarrier(NUM_RUNNERS, () ->
                System.out.println("Все бегуны на старте! Гонка началась!\n")
        );

        CyclicBarrier finishBarrier = new CyclicBarrier(NUM_RUNNERS, () -> {
            if (Runner.raceOngoing.get()) {
                System.out.println("Гонка завершена! Все финишировавшие бегуны достигли финиша.\n");
            } else {
                System.out.println("Гонка завершена досрочно из-за травмы одного из бегунов!\n");
            }
            System.exit(0);
        });

        ExecutorService executor = Executors.newFixedThreadPool(NUM_RUNNERS);

        for (int i = 1; i <= NUM_RUNNERS; i++) {
            executor.execute(new Runner(i, startBarrier, finishBarrier));
        }

        executor.shutdown();
    }
}

