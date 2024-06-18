package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.*;
import  ru.practicum.task_tracker.manager.Managers;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int idCount;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Subtask> subtasks;
    private final HashMap<Integer, Epic> epics;
    private final HistoryManager historyStorage;

    public InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
        historyStorage = Managers.getDefaultHistory();
    }

    //каждая новая созданная таска/сабтаска/эпик будет получать +1 к айдишнику
    private int getIdCount() {
        return idCount++;
    }

    @Override
    public ArrayList<Task> getHistory() {
        return historyStorage.getHistory();
    }

    //Получение списка всех задач
    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    //Получение списка всех эпиков
    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    //Получение списка всех подзадач
    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    //Удаление всех задач
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    //Удаление всех эпиков
    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    //Удаление всех подзадач
    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.setSubtasksForEpic(new ArrayList<>());
        }

    }

    //Получение задачи по идентификатору
    @Override
    public Task getTaskById(int taskId) {
        if (tasks.containsKey(taskId)) {
            historyStorage.add(tasks.get(taskId));
            return tasks.get(taskId);
        } else {
            return null;
        }
    }

    //Получение эпика по идентификатору
    @Override
    public Epic getEpicById(int epicId) {
        if (epics.containsKey(epicId)) {
            historyStorage.add(epics.get(epicId));
            return epics.get(epicId);
        } else {
            return null;
        }
    }

    //Получение подзадачи по идентификатору
    @Override
    public Subtask getSubtaskById(int subtaskId) {
        if (subtasks.containsKey(subtaskId)) {
            historyStorage.add(subtasks.get(subtaskId));
            return subtasks.get(subtaskId);
        } else {
            return null;
        }
    }

    //Создание задачи
    @Override
    public Task addTask(Task task) {
        task.setId(getIdCount());
        tasks.put(task.getId(), task);
        return task;
    }

    //Создание эпика
    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(getIdCount());
        epics.put(epic.getId(), epic);
        return epic;
    }

    //Создание подзадачи
    @Override
    public Subtask addSubtask(Subtask subtask) { //добавление задачи в мапу
        subtask.setId(getIdCount());
        subtasks.put(subtask.getId(), subtask);
        for (Epic epic : epics.values()) {
            if (!subtask.getEpic().equals(null) && subtask.getEpic().equals(epic)) {
                ArrayList<Subtask> merge = epic.getSubtasksForEpic();
                merge.add(subtask);
                epic.setSubtasksForEpic(merge);
            }
            epic.setStatus(epic.updateEpicStatus());
        }
        return subtask;
    }

    //Обновление задачи
    @Override
    public Task updateTask(Task task) {
        if (!tasks.containsKey(task.getId()) || task.getId() == null) {
            return null; //задел на try/catch NullPointerException
        }
        tasks.put(task.getId(), task);
        return task;
    }

    //Обновление эпика
    @Override
    public Epic updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId()) || epic.getId() == null) {
            return null;
        }
        epics.put(epic.getId(), epic);
        return epic;
    }

    //Обновление подзадачи
    @Override
    public Task updateSubtask(Subtask subtask) {
        if (!subtasks.containsKey(subtask.getId()) || subtask.getId() == null) {
            return null;
        }
        subtasks.put(subtask.getId(), subtask);
        for (Epic epic : epics.values()) {
            ArrayList<Subtask> merge = new ArrayList<>();
            for (Subtask subtaskMerge : subtasks.values()) {
                merge.add(subtaskMerge);
                if (subtaskMerge.getEpic().equals(epic)) {
                    epic.setSubtasksForEpic(merge);
                    epic.setStatus(epic.updateEpicStatus());
                }
            }
        }
        return subtask;
    }

    //Удаление задачи по идентификатору
    @Override
    public void deleteTaskById(int taskId) {
        tasks.remove(taskId); //подсмотрел на QA-вебинаре, понравилась реализация в 1 строку
    }

    //Удаление эпика по идентификатору
    @Override
    public void deleteEpicById(int epicId) {
        for (Subtask subtask : epics.get(epicId).getSubtasksForEpic()) {
            subtasks.remove(subtask.getId());
        }
        epics.remove(epicId);
    }

    //Удаление подзадачи по идентификатору
    @Override
    public void deleteSubtaskById(int subtaskId) {
        if (subtasks.containsKey(subtaskId)) {
            Epic epic = subtasks.get(subtaskId).getEpic();
            ArrayList<Subtask> subtaskList = epic.getSubtasksForEpic();
            subtaskList.remove(subtasks.get(subtaskId));
            epic.setSubtasksForEpic(subtaskList);
            subtasks.remove(subtaskId);
        }
    }

    //Получение списка всех подзадач определённого эпика
    @Override
    public ArrayList<Subtask> getAllSubtasksOfEpic(int epicId) {
        ArrayList<Subtask> result = new ArrayList<>();
        if (!epics.containsKey(epicId)) {
            return null;
        }
        for (Subtask sub : subtasks.values()) {
            if ((sub.getEpic().getId()).equals(epicId)) {
                result.add(sub);
            }
        }
        return result;
    }
}