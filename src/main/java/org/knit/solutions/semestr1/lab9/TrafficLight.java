package org.knit.solutions.semestr1.lab9;

public enum TrafficLight {
    RED("YELLOW"),
    YELLOW("GREEN"),
    GREEN("RED");

    private final String nextLight;

    TrafficLight(String nextLight) {
        this.nextLight = nextLight;
    }

    public TrafficLight getNext() {
        return TrafficLight.valueOf(nextLight);
    }
}
