package org.knit.solutions.semestr1.lab10;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UniversalUtilities {
    public static <T extends Comparable<T>> Optional<T> findMax(T[] elements) {
        return Arrays.stream(elements).max(Comparable::compareTo);
    }

    public static <T> List<T> filter(List<T> elements, Predicate<T> predicate) {
        return elements.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static <T> void printType(T obj) {
        System.out.println(obj.getClass());
    }
}
