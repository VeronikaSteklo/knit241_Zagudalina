package org.knit.solutions.semestr1.lab9;

import org.knit.solutions.semestr1.lab9.Retentions.MaxLength;
import org.knit.solutions.semestr1.lab9.Retentions.Min;
import org.knit.solutions.semestr1.lab9.Retentions.NotNull;

public class User {
    @NotNull
    private final String name;

    @MaxLength(10)
    private final String username;

    @Min(18)
    private final int age;

    // Конструкторы, геттеры и сеттеры
    public User(String name, String username, int age) {
        this.name = name;
        this.username = username;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}
