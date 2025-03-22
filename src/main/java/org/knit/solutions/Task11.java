package org.knit.solutions;

import org.knit.solutions.task11.EmailNotifier;
import org.knit.solutions.task11.MobileApp;
import org.knit.solutions.task11.Stock;
import org.knit.TaskDescription;

/*
Задача 11: Реализация системы уведомлений в биржевом приложении

Вы разрабатываете приложение для биржевой торговли, где пользователи могут подписываться на обновления акций определённых компаний.

Требуется:

Создать класс Stock (акция), который хранит информацию о текущей цене и может уведомлять подписчиков об изменении цены.
Создать интерфейс StockObserver и реализовать его в классах MobileApp и EmailNotifier, которые получают уведомления при изменении цены.
Реализовать механизм подписки и отписки для StockObserver.
Пример работы:

Пользователь подписывается на акции компании "Tesla".
Когда цена акции изменяется, приложение отправляет уведомления подписчикам.
Если пользователь отписался, он больше не получает уведомления.
Дополнительно: Добавьте возможность подписки на акции нескольких компаний.


 */


@TaskDescription(taskNumber = 11, taskDescription = "1. Паттерн Observer (Наблюдатель)")
public class Task11 implements Solution {

    @Override
    public void execute() {
        Stock stock1 = new Stock("Tesla", 1000);
        Stock stock2 = new Stock("Apple", 2000);

        MobileApp userT = new MobileApp("userT");
        EmailNotifier userA = new EmailNotifier("userA");

        stock1.registerObserver(userT);
        stock2.registerObserver(userA);

        stock1.setPrice(100);
        stock2.setPrice(100000);

        stock2.removeStock(userA);

        stock2.setPrice(1);
    }
}
