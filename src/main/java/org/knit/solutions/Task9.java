package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task9.Factory;

/*
Описание:
На перекрестке светофор управляет движением:

Красный свет – машины стоят (wait()).
Зеленый свет – машины едут (notifyAll()).
Светофор переключается каждые 5 секунд.
🔹 Что нужно реализовать?
✔ Поток "Светофор" изменяет цвет и отправляет notifyAll().
✔ Потоки "Машина" ждут wait(), если красный свет.
 */

@TaskDescription(taskNumber = 9, taskDescription = "Задача «Конвейер сборки деталей»")
public class Task9 implements Solution {
    @Override
    public void execute() {
        Factory factory = new Factory();
        factory.initProduction();
    }
}
