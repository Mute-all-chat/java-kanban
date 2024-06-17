package ru.practicum.task_tracker;

import ru.practicum.task_tracker.manager.*;
import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        TaskManager taskmanager = Managers.getDefault();

        //Создаём первую задачу
        Task task1 = new Task("Уборка", "Пропылесосить комнату, помыть окна", Status.NEW);
        Task task1added = taskmanager.addTask(task1);

        //Создаём вторую задачу
        System.out.println();
        Task task2 = new Task("Вынос мусора", "собрать всё в пакет и выкинуть", Status.NEW);
        Task task2added = taskmanager.addTask(task2);

        // Создаём эпик с двумя подзадачами
        System.out.println();
        Epic epic1 = new Epic("Клининг", "Много задач по уборке", new ArrayList<>());
        Epic epic1added = taskmanager.addEpic(epic1);
        Subtask subtask1 = new Subtask("Подметание", "Используй веник", Status.NEW, epic1);
        Subtask subtask1added = taskmanager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Помыть окна", "Средство в подвале", Status.NEW, epic1);
        Subtask subtask2added = taskmanager.addSubtask(subtask2);

        // Создаём эпик с одной подзадачей
        Epic epic2 = new Epic("Забота о питомцах", "Дела с пушистиками", new ArrayList<>());
        Epic epic2added = taskmanager.addEpic(epic2);
        Subtask subtask3 = new Subtask("Покормить котика", "Сухой корм", Status.NEW, epic2);
        Subtask subtask3added = taskmanager.addSubtask(subtask3);

        //Распечатаем задачи, подзадачи, эпики
        System.out.println();
        System.out.println(task1);
        System.out.println(task2);
        System.out.println(subtask1);
        System.out.println(subtask2);
        System.out.println(subtask3);
        System.out.println(epic1);
        System.out.println(epic2);

        //Изменим статусы созданных объектов и напечатаем их
        System.out.println();
        task1added.setStatus(Status.DONE);
        System.out.println("Задача 1 статус должен стать DONE: " + task1added.getStatus());
        task2added.setStatus(Status.IN_PROGRESS);
        System.out.println("Задача 2 статус должен стать IN_PROGRESS: " + task2added.getStatus());
        subtask1added.setStatus(Status.DONE);
        System.out.println("Подзадача 1 статус должен стать DONE: " + subtask1added.getStatus());
        subtask2added.setStatus(Status.DONE);
        System.out.println("Подзадача 2 статус должен стать DONE: " + subtask2added.getStatus());
        subtask3added.setStatus(Status.IN_PROGRESS);
        System.out.println("Подзадача 3 статус должен стать IN_PROGRESS: " + subtask3added.getStatus());
        epic1added.setStatus(epic1added.updateEpicStatus());
        System.out.println("Пересчитаем статус эпика 1, должен стать DONE: " + epic1added.getStatus());
        epic2added.setStatus(epic2added.updateEpicStatus());
        System.out.println("Пересчитаем статус эпика 2, должен стать IN_PROGRESS: " + epic2added.getStatus());

        //удалим одну задачу и один эпик
        taskmanager.deleteEpicById(epic1added.getId());
        System.out.println(taskmanager.getEpics());
        System.out.println(taskmanager.getSubtasks());
        taskmanager.deleteTaskById(task1added.getId());
        System.out.println(taskmanager.getTasks());

        //тест обновления подзадачи и удаления всех подзадач
        Subtask subtask4 = new Subtask("Покормить бобра", "предпочитает палки", Status.DONE, epic2);
        subtask4.setId(subtask3.getId());
        taskmanager.updateSubtask(subtask4);
        System.out.println();
        System.out.println(epic2);
        taskmanager.deleteAllSubtasks();
        System.out.println(epic2);

        //выполним "просмотр" эпика и задачи, выведем историю
        System.out.println();
        taskmanager.getEpicById(epic2.getId());
        taskmanager.getTaskById(task2.getId());
        System.out.println(taskmanager.getHistory());
    }
}