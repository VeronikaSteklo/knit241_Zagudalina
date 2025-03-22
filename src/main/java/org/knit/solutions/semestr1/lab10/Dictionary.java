package org.knit.solutions.semestr1.lab10;

import java.util.ArrayList;
import java.util.List;

public class Dictionary<K, V> {
    List<Pair<K, V>> pairList = new ArrayList<Pair<K, V>>();

    private Pair<K, V> getPair(K key) {
        return pairList.stream()
                .filter(p -> p.getFirst().equals(key))
                .findFirst()
                .get();
    }

    private boolean keyExists(K key) {
        return pairList.stream()
                .anyMatch(p -> p.getFirst().equals(key));
    }

    public V get(K key) {
        if (keyExists(key)) {
            return pairList.stream()
                    .filter(p -> p.getFirst().equals(key))
                    .findFirst()
                    .get()
                    .getSecond();
        } else {
            System.out.println("Значение по ключу " + key + " не было найдено");
            return null;
        }
    }

    public void put(K key, V value) {
        if (keyExists(key)) {
            Pair<K, V> objToChange = getPair(key);
            int indexToReplace = pairList.indexOf(objToChange);
            objToChange.setSecond(value);
            pairList.set(indexToReplace, objToChange);
        } else {
            pairList.add(new Pair<>(key, value));
        }
    }

    public void remove(K key) {
        if (keyExists(key)) {
            Pair<K, V> objToDelete = getPair(key);
            pairList.remove(objToDelete);
            System.out.println("Значение по ключу " + key + " удалено");
        } else {
            System.out.println("Значение для удаления по ключу " + key + " не было найдено");
        }
    }
}
