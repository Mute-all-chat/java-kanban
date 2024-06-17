package ru.practicum.task_tracker.task;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtaskTest {
    private static Subtask subtask;
    private static Epic epic;

    @BeforeEach
    public void beforeEach() {
        epic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        subtask = new Subtask("Тестовая подзадача", "Описание тестовой подзадачи", Status.NEW, null);
    }

    //В ТЗ был запрос на проверку возможности сделать объект Subtask своим же эпиком.
    // Данный тест не реализуем т.к. конструктор Subtask ожидает на вход Epic epic,
    // что автоматически исключает такую возможность.

    @Test
    public void setAndGetEpicTest() {
        assertNull(subtask.getEpic(), "У новой подзадачи может быть пустой эпик");
        subtask.setEpic(epic);
        assertEquals(epic, subtask.getEpic(), "Объект эпика подзадачи не совпадает");
    }

    @Test
    public void SubtaskEqualsTest() {
        Subtask subtask2 = new Subtask("Тестовая подзадача", "Описание тестовой подзадачи", Status.NEW, null);
        assertEquals(subtask2, subtask, "Подзадача не совпадает");
    }

    @Test
    public void SubtaskEqualsByIdTest() {
        Subtask subtask2 = new Subtask("Тестовая подзадача", "Описание тестовой подзадачи", Status.NEW, null);
        subtask2.setId(subtask.getId());
        assertEquals(subtask2.hashCode(), subtask.hashCode(), "Хэш не совпадает");
    }

    @Test
    public void SubtaskToStringTest() {
        subtask.setEpic(epic);
        String subtaskTestString = "ru.practicum.task_tracker.task.Subtask{" +
                "id=" + subtask.getId() +
                ", name='" + subtask.getName() + '\'' +
                ", description='" + subtask.getDescription() + '\'' +
                ", status=" + subtask.getStatus() +
                ", epicId=" + subtask.getEpic().getId() +
                '}';
        assertEquals(subtaskTestString, subtask.toString(), "Текстовое представление не совпадает");
    }
}