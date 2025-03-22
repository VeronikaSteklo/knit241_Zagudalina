package org.knit.solutions.semestr1.lab2;

import java.util.ArrayList;

public class Task4 {
    public class Container {
        private double volume;
        private ArrayList<Shape> innerShapes = new ArrayList<>();

        Container(double volume) {
            this.volume = volume;
        }

        public double getVolume() {
            return volume;
        }

        public double getFreeVolume() {
            double freeVolume = volume;
            for (Shape shape : innerShapes) {
                freeVolume -= shape.getVolume();
            }
            return freeVolume;
        }

        public boolean add(Shape shape) {
            if (shape.getVolume() <= getFreeVolume()) {
                innerShapes.add(shape);
                return true;
            }
            return false;
        }
    }

    public abstract class Shape {
        public abstract double getVolume();
    }

    public class Sphere extends Shape {
        private final double radius;

        Sphere(double radius) {
            this.radius = radius;
        }

        public double getVolume() {
            return 4.0 / 3 * Math.PI * Math.pow(radius, 3);
        }
    }

    public class Cube extends Shape {
        private final double side;

        Cube(double side) {
            this.side = side;
        }

        public double getVolume() {
            return Math.pow(side, 3);
        }
    }

    public class Cylinder extends Shape {
        private final double radius;
        private final double height;

        Cylinder(double radius, double height) {
            this.radius = radius;
            this.height = height;
        }

        public double getVolume() {
            return Math.PI * Math.pow(radius, 2) * height;
        }
    }

    public void execute() {
        Container container = new Container(1000);

        Shape sphere = new Sphere(5);
        Shape cube = new Cube(3);
        Shape cylinder = new Cylinder(5, 10);

        if (container.add(sphere)) {
            System.out.println("Сфера добавлена");
        } else {
            System.out.println("Недостаточно объема");
        }

        if (container.add(cube)) {
            System.out.println("Куб добавлен");
        } else {
            System.out.println("Недостаточно объема");
        }

        if (container.add(cylinder)) {
            System.out.println("Цилиндр добавлен");
        } else {
            System.out.println("Недостаточно объема");
        }
    }
}
