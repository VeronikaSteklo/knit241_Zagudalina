package org.knit.solutions;
import org.knit.TaskDescription;
import org.knit.solutions.task20.config.AppConfig;
import org.knit.solutions.task20.security.MasterPasswordHolder;
import org.knit.solutions.task20.service.PasswordService;
import org.knit.solutions.task20.clipboard.ClipboardCleaner;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
### Задача 20

- использование **Spring Context**,
- **AES-шифрование** паролей с мастер-паролем (`char[]`),
- команда для **копирования пароля в буфер обмена** (без вывода в консоль),
- возможное расширение на **JavaFX UI**.

---

## 🧪 Лабораторная работа: Password Manager с Spring и шифрованием

### 🎯 Цель работы:
Познакомиться с использованием **Spring Framework** для создания приложений со слоистой архитектурой, управлением зависимостями, конфигурацией контекста и безопасной работой с данными.

---

### 📌 Задание на 5 баллов:

Реализуйте приложение **Password Manager**, которое:
- позволяет сохранять логины и пароли для различных сайтов,
- использует **Spring Context** для управления зависимостями,
- **шифрует пароли с помощью мастер-пароля**,
- не показывает пароль на экран при просмотре, а копирует его в **буфер обмена по команде**,
- Реализовать **сохранение данных в файл** (`JSON`, `XML` или `Serialized`) между сессиями.
- (дополнительно) предоставляет графический интерфейс с JavaFX.

---

## ⚙️ Технические требования

### ✅ Основной функционал:

1. Приложение работает через консоль.
2. При запуске пользователь вводит **мастер-пароль** (не отображается на экране).
3. Все пароли шифруются с использованием алгоритма **AES** и введённого мастер-пароля.
4. Реализованы команды:
   - `add` — добавить запись (сайт, логин, пароль),
   - `list` — отобразить список сайтов и логинов (без паролей),
   - `copy <site>` — расшифровать пароль и **скопировать в буфер обмена**,
   - `delete <site>` — удалить запись по названию сайта,
   - `exit` — завершить программу.

---

### 🧱 Архитектура:

- **model/PasswordEntry.java** — класс с полями: `site`, `login`, `encryptedPassword`.
- **repository/PasswordRepository** — интерфейс + `InMemoryPasswordRepository` (использует `HashMap`).
- **service/PasswordService** — бизнес-логика: добавление, удаление, копирование.
- **crypto/EncryptionService** — интерфейс + `AesEncryptionService`.
- **security/MasterPasswordHolder** — хранит мастер-пароль в `char[]`.
- **clipboard/ClipboardService** — интерфейс + `SystemClipboardService` (реализация копирования в буфер).
- **config/AppConfig.java** — конфигурация Spring Context.
- **App.java** — точка входа, CLI-обработчик.

---

### 🔒 Безопасность:

- Мастер-пароль хранится в `char[]` и может быть **обнулён вручную** (в `shutdown hook`).
- Пароли не отображаются на экране при вводе и расшифровке.
- Шифрование происходит через `AES/CBC/PKCS5Padding`, ключ генерируется из мастер-пароля через `PBKDF2WithHmacSHA256`.

---

### 🧠 Дополнительно (по желанию):

- Добавить **графический интерфейс JavaFX**, в котором:
   - пользователь может добавить и удалить записи;
   - таблица отображает логины и сайты;
   - кнопка "Показать пароль" копирует его в буфер (не отображает);
   - мастер-пароль запрашивается при запуске.

---

## 🔧 Подсказки

### 📥 Чтение мастер-пароля:

```java
System.out.print("Введите мастер-пароль: ");
char[] masterPassword = System.console() != null
        ? System.console().readPassword()
        : scanner.nextLine().toCharArray();
```

---

### 📋 Копирование в буфер:

```java
Toolkit.getDefaultToolkit()
       .getSystemClipboard()
       .setContents(new StringSelection(password), null);
```

---

### 🧼 Очистка мастер-пароля в конце:

```java
Runtime.getRuntime().addShutdownHook(new Thread(() -> {
    MasterPasswordHolder holder = context.getBean(MasterPasswordHolder.class);
    holder.clear(); // обнулить char[] в памяти
}));
```

---

## 📁 Пример структуры проекта:

```
PasswordManager/
├── App.java
├── config/
│   └── AppConfig.java
├── model/
│   └── PasswordEntry.java
├── service/
│   └── PasswordService.java
├── crypto/
│   ├── EncryptionService.java
│   └── AesEncryptionService.java
├── security/
│   └── MasterPasswordHolder.java
├── repository/
│   ├── PasswordRepository.java
│   └── InMemoryPasswordRepository.java
├── clipboard/
│   ├── ClipboardService.java
│   └── SystemClipboardService.java
```

---

## 🧠 Что оценивается:
- корреткное разделение логики на слои;
- грамотное использование Spring для внедрения зависимостей;
- безопасная работа с данными (`char[]`, шифрование);
- умение работать с консольным вводом, буфером обмена;
- (дополнительно) использование JavaFX или сериализации.



---

### 🧩 Дополнительные задания (максимум +8 баллов)

#### 🔍 **1. Логгирование действий пользователя** (+2 балла)
- Использовать SLF4J + Logback (или аналогичный логгер).
- Логировать команды пользователя (`add`, `copy`, `delete`, `exit`) и исключения.
- Уровни логов:
    - `INFO` — действия пользователя,
    - `ERROR` — ошибки и сбои.

#### 💾 **2. Сохранение и загрузка данных в файл** (+3 балла)
- При запуске загружать данные из файла, при изменениях — сохранять автоматически.
- Форматы: JSON, XML или Java Serialization.
- ⚠️ **Файл должен быть полностью зашифрован с использованием мастер-пароля**.
  Например, можно:
    - сериализовать/сохранить в JSON;
    - зашифровать итоговую строку или байты через AES и сохранить;
    - при загрузке — расшифровать и восстановить объект.
- Ключ должен быть получен из мастер-пароля (PBKDF2).

#### ⏱️ **3. Асинхронная очистка буфера обмена** (+3 балла)
- После команды `copy` пароль попадает в буфер обмена.
- Через 30 секунд (или другой указанный интервал) буфер должен быть автоматически очищен.
- Очистка должна быть реализована через `ScheduledExecutorService` или `CompletableFuture.delayedExecutor`.

---
 */

@TaskDescription(taskNumber = 20, taskDescription = "Password Manager с Spring и шифрованием")
public class Task20 implements Solution{
    private static final Logger logger = LoggerFactory.getLogger(Task20.class);
    @Override
    public void execute() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Менеджер паролей\n");

        MasterPasswordHolder masterPasswordService = context.getBean(MasterPasswordHolder.class);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            masterPasswordService.clear();
            System.out.println("Мастер-пароль очищен из памяти (из shutdownHook).");
        }));

        PasswordService passwordService = context.getBean(PasswordService.class);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Команда (add/list/delete/copy/exit): ");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "add":
                    logger.info("Команда: add");
                    System.out.print("Имя сервиса: ");
                    String service = scanner.nextLine();
                    System.out.print("Логин: ");
                    String login = scanner.nextLine();
                    System.out.print("Пароль: ");
                    String password = scanner.nextLine();
                    passwordService.addPassword(service, login, password);
                    System.out.println("Пароль добавлен.");
                    break;
                case "list":
                    logger.info("Команда: list");
                    passwordService.listPasswords();
                    break;
                case "delete":
                    logger.info("Команда: delete");
                    System.out.print("Имя сервиса: ");
                    String deleteService = scanner.nextLine();
                    passwordService.deletePassword(deleteService);
                    System.out.println("Удалено.");
                    logger.info("Удален пароль для сервиса: {}", deleteService);
                    break;
                case "copy":
                    logger.info("Команда: copy");
                    System.out.print("Имя сервиса: ");
                    String copyService = scanner.nextLine();
                    passwordService.copyPasswordToClipboard(copyService);
                    logger.info("Пароль скопирован в буфер обмена для: {}", copyService);
                    break;
                case "exit":
                    logger.info("Команда: exit");
                    System.out.println("Выход...");
                    context.close();
                    return;
                default:
                    logger.info("Неизвестная команда: " + command);
                    System.out.println("Неизвестная команда.");
            }
        }
    }
}
