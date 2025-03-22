package org.knit.solutions.semestr1.lab10;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Number> {
    private List<T> numbers = new ArrayList<>();

    public void add(T number) {
        numbers.add(number);
    }

    public <T extends Number> T sum() {
        return numbers.stream()
                .map(Number::doubleValue)
                .reduce(Double::sum)
                .map(n -> (T) n)
                .get();
    }
}
