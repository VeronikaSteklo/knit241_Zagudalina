package org.knit.Solutions.Task4;

/*
📌 Описание:
На автозаправочной станции только 2 колонки. Если все заняты, машины ждут в очереди.
Когда колонка освобождается, следующий автомобиль из очереди начинает заправку.

🔹 Что нужно реализовать?
✔ Использовать wait() и notify() для ожидания и освобождения заправки.
✔ Поток "Машина" ждет, если все колонки заняты.
✔ Поток "Машина" заправляется, затем освобождает колонку.
 */

import org.knit.Solutions.TasksDescripton;

@TasksDescripton(number = 4, name = "2.4 Задача «Автозаправочная станция»")

public class Main {
    public static void main(String[] args) {
        GasStation gasStation = new GasStation();

        for (int i = 1; i <= 5; i++) {
            new Car("Машина " + i, gasStation).start();
        }
    }
}
