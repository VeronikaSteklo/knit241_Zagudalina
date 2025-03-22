package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task8.Car;
import org.knit.solutions.task8.TrafficLight;

@TaskDescription(taskNumber = 8, taskDescription = "Задача «Перекресток: светофор и машины»")
public class Task8 implements Solution {
    @Override
    public void execute() {
        TrafficLight trafficLight = new TrafficLight();

        Thread threadTrafficLight = new Thread(() -> {
            while (true) {
                trafficLight.changeColor();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadCar = new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                Car car = new Car(String.valueOf(i));
                car.pass(trafficLight);
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadTrafficLight.start();
        threadCar.start();
    }
}
