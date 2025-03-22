package org.knit.solutions.semestr1.lab5;

import java.util.Comparator;

public class ShopItemPriceComparator implements Comparator<ShopItem> {
    @Override
    public int compare(ShopItem o1, ShopItem o2) {
        if (o1.getPrice() == o2.getPrice())
            return 0;
        return o1.getPrice() > o2.getPrice() ? 1 : -1;
    }
}
