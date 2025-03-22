package org.knit.solutions.semestr1.lab2;

import java.util.Scanner;

public class Task3 {
    enum Operations {
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/");

        private final String title;

        Operations(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public static Operations fromValue(String title) {
            for (Operations enumConstant : Operations.values()) {
                if (enumConstant.title.equals(title)) {
                    return enumConstant;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public class DivisionByZeroException extends Exception {
        public DivisionByZeroException() { super(); }
    }

    class Calculator {
        public static final String availableOperations = "+-*/";

        public double add(double a, double b) {
            return a + b;
        }

        public double subtract(double a, double b) {
            return a - b;
        }

        public double multiply(double a, double b) {
            return a * b;
        }

        public double divide(double a, double b) throws DivisionByZeroException {
            if (b == 0) {
                throw new DivisionByZeroException();
            }
            return a / b;
        }
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        String input = "";

        while (true) {
            try {
                System.out.print("Введите первое число: ");
                input = scanner.nextLine();
                if (input.equals("exit"))
                    break;
                double a = Double.parseDouble(input.strip());

                System.out.print("Введите оператор (+, -, *, /): ");
                input = scanner.nextLine();
                if (input.equals("exit"))
                    break;
                Operations operation = Operations.fromValue(input.strip());

                System.out.print("Введите второе число: ");
                input = scanner.nextLine();
                if (input.equals("exit"))
                    break;
                double b = Double.parseDouble(input.strip());

                switch (operation) {
                    case ADD:
                        System.out.println(calculator.add(a, b));
                        break;
                    case DIVIDE:
                        System.out.println(calculator.divide(a, b));
                        break;
                    case MULTIPLY:
                        System.out.println(calculator.multiply(a, b));
                        break;
                    case SUBTRACT:
                        System.out.println(calculator.subtract(a, b));
                        break;
                }
            }
            catch (DivisionByZeroException e) {
                System.out.println("Делить на ноль нельзя");
            }
            catch (Exception e) {
                System.out.println("Некорректный ввод");
            }
            finally {
                System.out.println();
            }
        }
    }
}
