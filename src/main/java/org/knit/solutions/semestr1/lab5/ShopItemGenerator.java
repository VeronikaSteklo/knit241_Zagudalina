package org.knit.solutions.semestr1.lab5;

import java.util.Random;

public class ShopItemGenerator {
    String[] NAMES = {"книга", "ручка", "линейка", "пенал"};
    String[] PART_NUMBERS;

    public ShopItemGenerator() {
        PART_NUMBERS = new String[4];
        for (int i = 0; i < 4; i++) {
            PART_NUMBERS[i] = generateString(3);
        }
    }

    public ShopItem[] generateShopItems(int cnt) {
        Random random = new Random();
        ShopItem[] shopItems = new ShopItem[cnt];
        for (int i = 0; i < cnt; i++) {
            shopItems[i] = new ShopItem(
                    NAMES[random.nextInt(4)],
                    generateString(random.nextInt(20)),
                    random.nextInt(10000),
                    PART_NUMBERS[random.nextInt(4)]
            );
        }
        return shopItems;
    }

    private String generateString(int length)
    {
        StringBuilder characters = new StringBuilder();
        for (int i = 65; i < 91; i++) {
            characters.append((char) i);
        }

        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}
