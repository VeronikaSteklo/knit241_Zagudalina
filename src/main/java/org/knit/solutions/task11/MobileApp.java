package org.knit.solutions.task11;

public class MobileApp implements StockObserver {
    @Override
    public void notifyAboutPriceChange(String name, double oldPrice, double newPrice) {
        System.out.println("Уведомление в мобильном приложении:\n Цена на акцию " + name + " изменилась с " + oldPrice + " до " + newPrice);
    }
}
