package org.knit.solutions.semestr1.lab8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private static final String FILE_NAME = "users.ser";

    private List<User> users = new ArrayList<User>();

    public void start() {
        System.out.println("Старт программы...");
        if (loadAndPrintUsers())
            System.out.println("Список пользователей загружен");
        else
            System.out.println("Список пользователей не загрузился, начинаем с пустого.");

        Scanner scanner = new Scanner(System.in);
        String selectedOption = "";
        while (!selectedOption.equals("5")) {
            System.out.println("Меню:");
            System.out.println("1. Добавить пользователя");
            System.out.println("2. Показать всех студентов");
            System.out.println("3. Сохранить список пользователей в файл");
            System.out.println("4. Загрузить список пользователей из файла");
            System.out.println("5. Выйти из программы");

            System.out.println("\nВыберите опцию: ");
            selectedOption = scanner.nextLine();

            switch (selectedOption) {
                case "1":
                    System.out.println("Введите имя пользователя: ");
                    String name = scanner.nextLine();
                    System.out.println("Введите возраст пользователя: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите email пользователя: ");
                    String email = scanner.nextLine();

                    users.add(new User(name, age, email));
                    System.out.println("Пользователь успешно добавлен");
                    break;

                case "2":
                    System.out.println("Список пользователей: ");
                    for (int i = 0; i < users.size(); i++) {
                        System.out.println((i+1) + ". " + users.get(i).toString());
                    }
                    break;

                case "3":
                    if (saveUsers())
                        System.out.println("Список успешно сохранен.");
                    else
                        System.out.println("Список не сохранен.");
                    break;

                case "4":
                    if (loadAndPrintUsers())
                        System.out.println("Список успешно загружен.");
                    else
                        System.out.println("Список не загрузился");
                    break;

                case "5":
                    System.out.println("Выход из программы...");
                    break;

                default:
                    System.out.println("Некорректный ввод. Повторите попытку.");
                    break;
            }
        }
    }

    private boolean saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"))) {
            oos.writeObject(users);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean loadAndPrintUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"))) {
            List<User> deserializedUsers = (List<User>) ois.readObject();
            for (int i = 0; i < deserializedUsers.size(); i++) {
                System.out.println((i+1) + ". " + deserializedUsers.get(i).toString());
            }
            users = deserializedUsers;
            return true;
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл со списком пока не был создан.");
            return false;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
