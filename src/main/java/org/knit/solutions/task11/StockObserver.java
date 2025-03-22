package org.knit.solutions.task11;

public interface StockObserver {
    void notifyAboutPriceChange(String name, double oldPrice, double newPrice);
}
