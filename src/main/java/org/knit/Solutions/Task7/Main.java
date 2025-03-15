package org.knit.Solutions.Task7;

/*
📌 Описание:

Производитель создает товары (максимум 5).
Потребитель забирает товары.
Если товаров нет, потребитель ждет (wait()).
Если товаров максимум, производитель ждет (wait()).
🔹 Что нужно реализовать?
✔ wait() – если товаров нет или склад заполнен.
✔ notify() – пробуждение потока, когда изменяется состояние склада.
 */

import org.knit.Solutions.TasksDescripton;

@TasksDescripton(number = 7, name = "2.7 Задача «Производитель-Потребитель с ограничением»")

public class Main {
    public static void main(String[] args){
        Warehouse warehouse = new Warehouse();

        Consumer consumer = new Consumer(warehouse);
        Manufacturer manufacturer = new Manufacturer(warehouse);

        consumer.start();
        manufacturer.start();
    }
}
