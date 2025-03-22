package org.knit.solutions;

/*
Задача 12: Реализация системы отмены изменений в текстовом редакторе

Разработайте систему, позволяющую сохранять состояния текста в редакторе и откатывать изменения назад.

Требуется:
Создать класс TextEditor, который содержит текущий текст документа.
Создать класс Memento, который будет хранить снимок состояния текста.
Реализовать Caretaker, который управляет историей изменений (например, стек сохранённых состояний).
Реализовать методы saveState() и undo() для сохранения и отката изменений.

Пример работы:
Пользователь вводит текст "Hello, world!".
Он сохраняет текущее состояние.
Затем добавляет " How are you?".
Нажимает "Отменить" и возвращается к "Hello, world!".

Дополнительно: Реализуйте возможность нескольких уровней отката.
 */

import org.knit.TaskDescription;
import org.knit.solutions.task12.Caretaker;
import org.knit.solutions.task12.TextEditor;

@TaskDescription(taskNumber = 12, taskDescription = "Паттерн Memento (Хранитель)")
public class Task12 implements Solution {
    @Override
    public void execute() {
        Caretaker caretaker = new Caretaker();
        TextEditor textEditor = new TextEditor(caretaker);

        textEditor.addText("qwerty");
        textEditor.saveState();
        textEditor.addText(" 123");
        textEditor.saveState();
        textEditor.addText(" hehehehe");
        textEditor.saveState();

        System.out.println(textEditor.getCurrentText());
        textEditor.undo();
        System.out.println(textEditor.getCurrentText());
        textEditor.undo();
        System.out.println(textEditor.getCurrentText());
        textEditor.undo();
        System.out.println(textEditor.getCurrentText());
        textEditor.undo();
        textEditor.undo();
        System.out.println(textEditor.getCurrentText());
    }
}
