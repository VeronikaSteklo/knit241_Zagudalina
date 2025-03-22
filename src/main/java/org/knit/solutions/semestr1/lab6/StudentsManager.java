package org.knit.solutions.semestr1.lab6;

import java.util.Scanner;
import java.util.TreeSet;

public class StudentsManager {
    private final TreeSet<String> students = new TreeSet<>();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String selectedOption = "";
        while (!selectedOption.equals("5")) {
            System.out.println("Меню:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента");
            System.out.println("3. Показать всех студентов");
            System.out.println("4. Найти студента по имени");
            System.out.println("5. Выйти из программы");

            System.out.println("\nВыберите опцию: ");
            selectedOption = scanner.nextLine();

            switch (selectedOption) {
                case "1":
                    System.out.println("Введите имя студента: ");
                    String studentToAdd = scanner.nextLine();
                    if (students.contains(studentToAdd)) {
                        System.out.println("Студент с таким именем уже есть.");
                    } else {
                        students.add(studentToAdd);
                        System.out.println("Студент добавлен.");
                    }
                    break;
                case "2":
                    System.out.println("Введите имя студента для удаления: ");
                    String studentToDelete = scanner.nextLine();
                    if (students.contains(studentToDelete)) {
                        students.remove(studentToDelete);
                        System.out.println("Студент удален.");
                    } else {
                        System.out.println("Такого студента в списке нет.");
                    }
                    break;
                case "3":
                    System.out.println("Произвести поиск по диапазону? (y/n)");
                    if (scanner.nextLine().equals("y")) {
                        System.out.println("Введите первого студента:");
                        String firstStudent = scanner.nextLine();
                        System.out.println("Введите второго студента:");
                        String secondStudent = scanner.nextLine();

                        if (!students.contains(firstStudent)) {
                            System.out.println("Первого студента в списке нет.");
                        } else if (!students.contains(secondStudent)) {
                            System.out.println("Второго студента в списке нет.");
                        } else {
                            System.out.println(String.join("\n", students.subSet(firstStudent, secondStudent)));
                        }

                    } else {
                        for (String student : students) {
                            System.out.println(student);
                        }
                    }
                    break;
                case "4":
                    System.out.println("Введите имя студента для поиска: ");
                    String studentToFind = scanner.nextLine();
                    if (students.contains(studentToFind))
                        System.out.println("Студент найден.");
                    else
                        System.out.println("Студент не найден.");
                    break;
                case "5":
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Некорректный ввод. Повторите попытку.");
                    break;
            }
            System.out.println();
        }
    }
}
