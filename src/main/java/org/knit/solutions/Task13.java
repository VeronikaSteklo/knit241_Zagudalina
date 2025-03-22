package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task13.*;

/*
Задача 13: Реализация системы команд для управления умным домом

Вы разрабатываете систему управления "умным домом", где пользователь может выполнять различные команды, такие как включение/выключение света, управление телевизором и кондиционером.

Требуется:
Создать интерфейс Command, который определяет метод execute().
Реализовать классы команд:
LightOnCommand – включает свет.
LightOffCommand – выключает свет.
TVOnCommand – включает телевизор.
TVOffCommand – выключает телевизор.
Создать класс RemoteControl, который хранит команду и позволяет её выполнить.
Реализовать механизм отмены последней команды (undo).

Пример работы:
Пользователь нажимает кнопку "Включить свет" – выполняется LightOnCommand.
Затем нажимает "Выключить телевизор" – выполняется TVOffCommand.
Пользователь нажимает "Отменить последнюю команду" – телевизор снова включается.

Дополнительно: Реализуйте поддержку макрокоманд (например, кнопка "Спокойной ночи" выключает все приборы разом).
 */

@TaskDescription(taskNumber = 13, taskDescription = "Паттерн Command (Команда)")
public class Task13 implements Solution {

    @Override
    public void execute() {
        RemoteControl remoteControl = new RemoteControl();
        TV tv = new TV();
        Lights lights = new Lights();

        Command turnOffTV = new TVOffCommand(tv);
        Command turnOnTV = new TVOnCommand(tv);

        Command lightsOffTV = new LightOffCommand(lights);
        Command lightsOnTV = new LightOnCommand(lights);

        remoteControl.executeCommand(turnOnTV);
        remoteControl.executeCommand(lightsOnTV);
        remoteControl.executeCommand(lightsOffTV);
        remoteControl.executeCommand(turnOffTV);

        remoteControl.undoLastCommand();

        remoteControl.executeCommand(lightsOnTV);
        remoteControl.goodnight(tv, lights);
    }
}
