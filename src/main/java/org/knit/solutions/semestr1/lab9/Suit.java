package org.knit.solutions.semestr1.lab9;

public enum Suit {
    Spades("Пики"),
    Hearts("Черви"),
    Diamonds("Бубны"),
    Clubs("Трефы");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
