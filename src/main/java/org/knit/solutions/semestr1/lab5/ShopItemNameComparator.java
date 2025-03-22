package org.knit.solutions.semestr1.lab5;

import java.util.Comparator;

public class ShopItemNameComparator implements Comparator<ShopItem> {
    @Override
    public int compare(ShopItem o1, ShopItem o2) {
        if (o1.getName().equals(o2.getName()))
            return 0;
        return o1.getName().compareTo(o2.getName());
    }
}
