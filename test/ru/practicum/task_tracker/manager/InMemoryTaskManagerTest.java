package ru.practicum.task_tracker.manager;

import org.junit.jupiter.api.*;
import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class InMemoryTaskManagerTest {
    private static TaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    public void getHistoryTest() {
        Epic epic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        int epicId = taskManager.addEpic(epic).getId();
        Epic savedEpic = taskManager.getEpicById(epicId);

        assertTrue(taskManager.getHistory().contains(savedEpic));
    }

    @Test
    public void addAndGetSubtaskTest() {
        Subtask subtask = new Subtask("Тестовая подзадача", "Описание тестовой подзадачи", Status.NEW, null);
        int subtaskId = taskManager.addSubtask(subtask).getId();
        Subtask savedSubtask = taskManager.getSubtaskById(subtaskId);

        assertNotNull(savedSubtask, "Подзадача не найдена.");
        assertEquals(subtask.getId(), savedSubtask.getId(), "ID не совпадает");
        assertEquals(subtask.getName(), savedSubtask.getName(), "Наименование не совпадает");
        assertEquals(subtask.getDescription(), savedSubtask.getDescription(), "Описание не совпадает");
        assertEquals(subtask.getStatus(), savedSubtask.getStatus(), "Статус не совпадает");
    }

    @Test
    public void addAndGetEpicTest() {
        Epic newEpic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        int epicId = taskManager.addEpic(newEpic).getId();
        Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(newEpic.getId(), savedEpic.getId(), "ID не совпадает");
        assertEquals(newEpic.getName(), savedEpic.getName(), "Наименование не совпадает");
        assertEquals(newEpic.getDescription(), savedEpic.getDescription(), "Описание не совпадает");
        assertEquals(newEpic.getStatus(), savedEpic.getStatus(), "Статус не совпадает");
    }

    @Test
    public void addAndGetTaskTest() {
        Task newTask = new Task("Тестовая задача", "Описание тестовой задачи", Status.NEW);
        int taskId = taskManager.addTask(newTask).getId();
        Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Тестовая задача не найдена.");
        assertEquals(newTask.getId(), savedTask.getId(), "ID не совпадает");
        assertEquals(newTask.getName(), savedTask.getName(), "Наименование не совпадает");
        assertEquals(newTask.getDescription(), savedTask.getDescription(), "Описание не совпадает");
        assertEquals(newTask.getStatus(), savedTask.getStatus(), "Статус не совпадает");
    }

    @Test
    public void updateTaskTest() {
        Task task = new Task("Тестовая задача", "Описание тестовой задачи", Status.NEW);
        int taskId = taskManager.addTask(task).getId();
        Task updatedTask = new Task("Тестовая задача изменена", "Описание тестовой задачи изменено", Status.DONE);
        updatedTask.setId(taskId);
        taskManager.updateTask(updatedTask);

        assertEquals(updatedTask, taskManager.getTaskById(taskId), "Задача не совпадает");
        assertEquals(updatedTask.getId(), taskManager.getTaskById(taskId).getId(), "ID не совпадает");
        assertEquals(updatedTask.getName(), taskManager.getTaskById(taskId).getName(), "Наименование не совпадает");
        assertEquals(updatedTask.getDescription(), taskManager.getTaskById(taskId).getDescription(), "Описание не совпадает");
        assertEquals(updatedTask.getStatus(), taskManager.getTaskById(taskId).getStatus(), "Статус не совпадает");
    }

    @Test
    public void updateSubtaskTest() {
        Epic epic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        Epic epic2 = new Epic("Тестовый эпик - 2", "Описание тестового эпика - 2", new ArrayList<>());
        Subtask subtask = new Subtask("Тестовая подзадача", "Описание тестовой подзадачи", Status.NEW, epic);
        int subtaskId = taskManager.addSubtask(subtask).getId();
        Subtask updatedSubtask = new Subtask("Тестовая подзадача изменена", "Описание тестовой подзадачи изменено", Status.DONE, epic2);
        updatedSubtask.setId(subtaskId);
        taskManager.updateSubtask(updatedSubtask);

        assertEquals(updatedSubtask, taskManager.getSubtaskById(subtaskId), "Подзадача не совпадает");
        assertEquals(updatedSubtask.getId(), taskManager.getSubtaskById(subtaskId).getId(), "ID не совпадает");
        assertEquals(updatedSubtask.getName(), taskManager.getSubtaskById(subtaskId).getName(), "Наименование не совпадает");
        assertEquals(updatedSubtask.getDescription(), taskManager.getSubtaskById(subtaskId).getDescription(), "Описание не совпадает");
        assertEquals(updatedSubtask.getStatus(), taskManager.getSubtaskById(subtaskId).getStatus(), "Статус не совпадает");
    }

    @Test
    public void updateEpicTest() {
        Epic epic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        int epicId = taskManager.addEpic(epic).getId();
        Epic updatedEpic = new Epic("Тестовый эпик изменен", "Описание тестового эпика изменено", new ArrayList<>());
        updatedEpic.setId(epicId);
        taskManager.updateEpic(updatedEpic);

        assertEquals(updatedEpic, taskManager.getEpicById(epicId), "Задача не совпадает");
        assertEquals(updatedEpic.getId(), taskManager.getEpicById(epicId).getId(), "ID не совпадает");
        assertEquals(updatedEpic.getName(), taskManager.getEpicById(epicId).getName(), "Наименование не совпадает");
        assertEquals(updatedEpic.getDescription(), taskManager.getEpicById(epicId).getDescription(), "Описание не совпадает");
        assertEquals(updatedEpic.getStatus(), taskManager.getEpicById(epicId).getStatus(), "Статус не совпадает");
    }

    @Test
    public void getAllAndDeleteAllTasksTest() {
        Task task1 = new Task("Первая задача", "Описание первой задачи", Status.NEW);
        Task task2 = new Task("Вторая задача", "Описание второй задачи", Status.NEW);
        Task task3 = new Task("Третья задача", "Описание третьей задачи", Status.NEW);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        final ArrayList<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(3, tasks.size(), "Неверное количество задач");
        assertTrue(tasks.contains(task1), "Список всех задач не содержит добавленную задачу");
        assertTrue(tasks.contains(task2), "Список всех задач не содержит добавленную задачу");
        assertTrue(tasks.contains(task3), "Список всех задач не содержит добавленную задачу");
        taskManager.deleteTaskById(task1.getId());
        final ArrayList<Task> tasksAfterDelete = taskManager.getTasks();
        assertEquals(2, tasksAfterDelete.size(), "Неверное количество задач после удаления");
        assertFalse(tasksAfterDelete.contains(task1), "Список всех задач содержит удаленную задачу");
        taskManager.deleteAllTasks();
        final ArrayList<Task> tasksAfterDeleteAll = taskManager.getTasks();
        assertEquals(0, tasksAfterDeleteAll.size(), "Неверное количество задач после удаления всех");
    }

    @Test
    public void getAllAndDeleteAllEpicsTest() {
        Epic epic1 = new Epic("Первый эпик", "Описание первого эпика", new ArrayList<>());
        Epic epic2 = new Epic("Второй эпик", "Описание второго эпика", new ArrayList<>());
        Epic epic3 = new Epic("Третий эпик", "Описание третьего эпика", new ArrayList<>());
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addEpic(epic3);
        final ArrayList<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(3, epics.size(), "Неверное количество эпиков");
        assertTrue(epics.contains(epic1), "Список всех эпиков не содержит добавленный эпик");
        assertTrue(epics.contains(epic2), "Список всех эпиков не содержит добавленный эпик");
        assertTrue(epics.contains(epic3), "Список всех эпиков не содержит добавленный эпик");
        taskManager.deleteEpicById(epic1.getId());
        final ArrayList<Epic> epicsAfterDelete = taskManager.getEpics();
        assertEquals(2, epicsAfterDelete.size(), "Неверное количество эпиков после удаления");
        assertFalse(epicsAfterDelete.contains(epic1), "Список всех эпиков содержит удаленный эпик");
        taskManager.deleteAllEpics();
        final ArrayList<Epic> epicsAfterDeleteAll = taskManager.getEpics();
        assertEquals(0, epicsAfterDeleteAll.size(), "Неверное количество эпиков после удаления всех");
    }

    @Test
    public void getAllAndDeleteAllSubtasksTest() {
        Epic epic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        Subtask subtask1 = new Subtask("Первая подзадача", "Описание первой подзадачи", Status.NEW, epic);
        Subtask subtask2 = new Subtask("Вторая подзадача", "Описание второй подзадачи", Status.NEW, epic);
        Subtask subtask3 = new Subtask("Третья подзадача", "Описание третьей подзадачи", Status.NEW, epic);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        final ArrayList<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Подзадачи не возвращаются.");
        assertEquals(3, subtasks.size(), "Неверное количество подзадач");
        assertTrue(subtasks.contains(subtask1), "Список всех подзадач не содержит добавленную подзадачу");
        assertTrue(subtasks.contains(subtask2), "Список всех подзадач не содержит добавленную подзадачу");
        assertTrue(subtasks.contains(subtask3), "Список всех подзадач не содержит добавленную подзадачу");
        taskManager.deleteSubtaskById(subtask1.getId());
        final ArrayList<Subtask> tasksAfterDelete = taskManager.getSubtasks();
        assertEquals(2, tasksAfterDelete.size(), "Неверное количество подзадач после удаления");
        assertFalse(tasksAfterDelete.contains(subtask1), "Список всех подзадач содержит удаленную подзадачу");
        taskManager.deleteAllSubtasks();
        final ArrayList<Subtask> tasksAfterDeleteAll = taskManager.getSubtasks();
        assertEquals(0, tasksAfterDeleteAll.size(), "Неверное количество подзадач после удаления всех");
    }

    @Test
    public void getAllSubtasksOfEpicTest() {
        Epic epic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        int epicId = taskManager.addEpic(epic).getId();
        Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Эпик не найден");
        assertEquals(epicId, savedEpic.getId(), "ID не совпадает");
        assertEquals("Тестовый эпик", savedEpic.getName(), "Наименование не совпадает");
        assertEquals("Описание тестового эпика", savedEpic.getDescription(), "Описание не совпадает");
        assertEquals(Status.NEW, savedEpic.getStatus(), "Статус не совпадает");

        Subtask subtask = new Subtask("Первая подзадача", "Описание первой подзадачи", Status.IN_PROGRESS, epic);
        taskManager.addSubtask(subtask);

        assertTrue(taskManager.getAllSubtasksOfEpic(epicId).contains(subtask), "Список подзадач эпика не содержит подзадачи");
    }
}