package org.knit.Solutions.Task5;

/*
📌 Описание:
В ресторане работает один повар и один официант.

Повар готовит блюда и ставит их на поднос (максимум 3 блюда).
Официант берет готовые блюда и подает их клиентам.
🔹 Что нужно реализовать?
✔ Повар не может готовить больше 3 блюд (ждет wait()).
✔ Официант ждет, если поднос пуст (wait()).
✔ При каждой передаче блюда используется notify().
 */

import org.knit.Solutions.TasksDescripton;

@TasksDescripton(number = 5, name = "2.5 Задача «Ресторан: Повар и Официант»")


public class Main {
    public static void main(String[] args){
        Restaurant restaurant = new Restaurant();

        Chef chef = new Chef(restaurant);
        Waither waither = new Waither(restaurant);

        chef.start();
        waither.start();
    }
}
