package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.Epic;
import ru.practicum.task_tracker.task.Subtask;
import ru.practicum.task_tracker.task.Task;

import java.util.ArrayList;

public interface TaskManager {
    //Получение списка всех задач
    ArrayList<Task> getTasks();

    //Получение списка всех эпиков
    ArrayList<Epic> getEpics();

    //Получение списка всех подзадач
    ArrayList<Subtask> getSubtasks();

    //Удаление всех задач
    void deleteAllTasks();

    //Удаление всех эпиков
    void deleteAllEpics();

    //Удаление всех подзадач
    void deleteAllSubtasks();

    //Получение задачи по идентификатору
    Task getTaskById(int taskId);

    //Получение эпика по идентификатору
    Epic getEpicById(int epicId);

    //Получение подзадачи по идентификатору
    Subtask getSubtaskById(int subtaskId);

    //Создание задачи
    Task addTask(Task task);

    //Создание эпика
    Epic addEpic(Epic epic);

    //Создание подзадачи
    Subtask addSubtask(Subtask subtask);

    //Обновление задачи
    Task updateTask(Task task);

    //Обновление эпика
    Epic updateEpic(Epic epic);

    //Обновление подзадачи
    Task updateSubtask(Subtask subtask);

    //Удаление задачи по идентификатору
    void deleteTaskById(int taskId);

    //Удаление эпика по идентификатору
    void deleteEpicById(int epicId);

    //Удаление подзадачи по идентификатору
    void deleteSubtaskById(int subtaskId);

    //Получение списка всех подзадач определённого эпика
    ArrayList<Subtask> getAllSubtasksOfEpic(int epicId);

    ArrayList<Task> getHistory();
}