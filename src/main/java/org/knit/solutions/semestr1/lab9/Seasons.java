package org.knit.solutions.semestr1.lab9;

public enum Seasons {
    WINTER("Зима", "Холодно", "НГ"),
    SPRING("Весна", "Сначала холодно, потом жарко", "Масленица"),
    SUMMER("Лето", "Жарко", "День независимости"),
    AUTUMN("Осень", "Тепло", "1-ое сентября");

    private final String name;
    private final String temperature;
    private final String holiday;

    Seasons(String name, String temperature, String holiday) {
        this.name = name;
        this.temperature = temperature;
        this.holiday = holiday;
    }

    public String getInfo() {
        return name + ": " + temperature + ", один из праздников - " + holiday;
    }
}
