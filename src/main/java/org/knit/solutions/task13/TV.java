package org.knit.solutions.task13;

public class TV {
    private boolean isOn = false;

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
        if (isOn) {
            System.out.println("Телевизор теперь включен");
        } else {
            System.out.println("Телевизор теперь выключен");
        }
    }
}
