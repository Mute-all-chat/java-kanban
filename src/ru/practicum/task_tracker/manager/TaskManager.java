package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int idCount;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    //каждая новая созданная таска/сабтаска/эпик будет получать +1 к айдишнику
    private int getIdCount() {
        return idCount++;
    }

    //Получение списка всех задач
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    //Получение списка всех эпиков
    public ArrayList<Task> getEpics() {
        return new ArrayList<>(epics.values());
    }

    //Получение списка всех подзадач
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    //Удаление всех задач
    public void deleteAllTasks() {
        tasks.clear();
    }

    //Удаление всех эпиков
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    //Удаление всех подзадач
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.setSubtasksForEpic(new ArrayList<>());
            epic.setStatus(Status.NEW);
        }

    }

    //Получение задачи по идентификатору
    public Task getTaskById(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        } else {
            return null;
        }
    }

    //Получение эпика по идентификатору
    public Epic getEpicById(int epicId) {
        if (epics.containsKey(epicId)) {
            return epics.get(epicId);
        } else {
            return null;
        }
    }

    //Получение подзадачи по идентификатору
    public Subtask getSubtaskById(int subtaskId) {
        if (subtasks.containsKey(subtaskId)) {
            return subtasks.get(subtaskId);
        } else {
            return null;
        }
    }

    //Создание задачи
    public Task addTask(Task task) {
        task.setId(getIdCount());
        tasks.put(task.getId(), task);
        return task;
    }

    //Создание эпика
    public Epic addEpic(Epic epic) {
        epic.setId(getIdCount());
        epics.put(epic.getId(), epic);
        return epic;
    }

    //Создание подзадачи
    public Subtask addSubtask(Subtask subtask) { //добавление задачи в мапу
        subtask.setId(getIdCount());
        subtasks.put(subtask.getId(), subtask);
        for (Epic epic : epics.values()) {
            epic.updateEpicStatus();
        }
        return subtask;
    }

    //Обновление задачи
    public Task updateTask(Task task) {
        if (!tasks.containsKey(task.getId()) || task.getId() == null) {
            return null; //задел на try/catch NullPointerException
        }
        tasks.put(task.getId(), task);
        return task;
    }

    //Обновление эпика
    public Epic updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId()) || epic.getId() == null) {
            return null;
        }
        epics.put(epic.getId(), epic);
        return epic;
    }

    //Обновление подзадачи
    public Task updateSubtask(Subtask subtask) {
        if (!subtasks.containsKey(subtask.getId()) || subtask.getId() == null) {
            return null;
        }
        subtasks.put(subtask.getId(), subtask);
        for (Epic epic : epics.values()) {
            epic.updateEpicStatus();
        }
        return subtask;
    }

    //Удаление задачи по идентификатору
    public void deleteTaskById(int taskId) {
        tasks.remove(taskId); //подсмотрел на QA-вебинаре, понравилась реализация в 1 строку
    }

    //Удаление эпика по идентификатору
    public void deleteEpicById(int epicId) {
        for (Subtask subtask : epics.get(epicId).getSubtasksForEpic()) {
            subtasks.remove(subtask.getId());
        }
        epics.remove(epicId);
    }

    //Удаление подзадачи по идентификатору
    public void deleteSubtaskById(int subtaskId) {
        if (subtasks.containsKey(subtaskId)) {
            Epic epic = subtasks.get(subtaskId).getEpic();
            ArrayList<Subtask> subtaskList = epic.getSubtasksForEpic();
            subtaskList.remove(subtasks.get(subtaskId));
            epic.setSubtasksForEpic(subtaskList);
            subtasks.remove(subtaskId);
            epic.setStatus(epic.updateEpicStatus());
        }
    }


    //Получение списка всех подзадач определённого эпика
    public ArrayList<Subtask> getAllSubtasksOfEpic(int epicId) {
        ArrayList<Subtask> result = new ArrayList<>();
        if (!epics.containsKey(epicId)) {
            return null;
        }
        for (Subtask sub : subtasks.values()) {
            if (sub.getEpic().equals(epicId)) {
                result.add(sub);
            }
        }
        return result;
    }
}
