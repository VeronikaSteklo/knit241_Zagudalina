package org.knit.solutions.task13;

public class Lights {
    private boolean isOn = false;

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
        if (isOn) {
            System.out.println("Свет теперь включен");
        } else {
            System.out.println("Свет теперь выключен");
        }
    }
}
