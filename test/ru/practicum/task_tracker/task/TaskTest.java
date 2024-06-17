package ru.practicum.task_tracker.task;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    static Task task;

    @BeforeEach
    public void beforeEach() {
        task = new Task("Тестовая задача", "Описание тестовой задачи", Status.NEW);
    }

    @Test
    public void TaskEqualsTest() {
        Task task2 = new Task("Тестовая задача", "Описание тестовой задачи", Status.NEW);
        assertEquals(task2, task, "Таска не совпадает");
    }

    @Test
    public void getAndSetTaskIdTest() {
        int taskId = 1;
        task.setId(taskId);
        assertEquals(1, task.getId(), "ID не совпадает");
    }

    @Test
    public void addNewTaskTest() {
        assertNotNull(task, "Задача не найдена.");
        assertEquals("Тестовая задача", task.getName(), "Название не совпадает");
        assertEquals("Описание тестовой задачи", task.getDescription(), "Описание не совпадает");
        assertEquals(Status.NEW, task.getStatus(), "Статус не совпадает");
    }

    @Test
    public void getAndSetTaskNameTest() {
        assertEquals("Тестовая задача", task.getName(), "Название не совпадает");
        task.setName("Новое название");
        assertEquals("Новое название", task.getName(), "Новое название не совпадает");
    }

    @Test
    public void getAndSetTaskDescriptionTest() {
        assertEquals("Описание тестовой задачи", task.getDescription(), "Описание не совпадает");
        task.setDescription("Новое описание");
        assertEquals("Новое описание", task.getDescription(), "Новое описание не совпадает");
    }

    @Test
    public void getAndSetTaskStateTest() {
        assertEquals(Status.NEW, task.getStatus(), "Статус не совпадает");
        task.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS, task.getStatus(), "Новый статус не совпадает");
    }

    @Test
    public void TaskHashCodeTest() {
        Task task2 = new Task("Тестовая задача", "Описание тестовой задачи", Status.NEW);
        assertEquals(task2.hashCode(), task.hashCode(), "Хэш не совпадает");
    }

    @Test
    public void TaskEqualsByIdTest() {
        Task task2 = new Task("Тестовая задача", "Описание тестовой задачи", Status.NEW);
        task2.setId(task.getId());
        assertEquals(task2.hashCode(), task.hashCode(), "Хэш не совпадает");
    }

    @Test
    public void TaskToStringTest() {
        String taskTestString = "ru.practicum.task_tracker.task.Task{" +
                "id=" + task.getId() +
                ", name='" + task.getName() + '\'' +
                ", description='" + task.getDescription() + '\'' +
                ", status=" + task.getStatus() +
                '}';
        assertEquals(taskTestString, task.toString(), "Текстовое представление не совпадает");
    }
}