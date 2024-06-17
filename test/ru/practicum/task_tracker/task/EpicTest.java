package ru.practicum.task_tracker.task;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

class EpicTest {
    private static Epic epic;

    @BeforeEach
    public void beforeEach() {
        epic = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
    }

    //В ТЗ был запрос на проверку добавления Epic в самого себя в виде подзадачи. Данный тест не реализуем т.к. subtasks
    // метод setSubtasksForEpic ожидает на вход ArrayList<Subtask> что автоматически исключает такую возможность.

    @Test
    public void setAndGetSubtaskTest() {
        Subtask subtask = new Subtask("Подзадача", "Описание подзадачи", Status.NEW, epic);
        ArrayList<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask);
        epic.setSubtasksForEpic(subtasks);

        assertEquals(subtasks, epic.getSubtasksForEpic(), "Не совпадает объект списка подзадач эпика");
        assertEquals(subtask, epic.getSubtasksForEpic().get(0), "Не совпадает объект подзадачи списка подзадач эпика");
    }

    @Test
    public void EpicEqualsTest() {
        Epic epic2 = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        assertEquals(epic2, epic, "Эпик не совпадает");
    }

    @Test
    public void EpicEqualsByIdTest() {
        Epic epic2 = new Epic("Тестовый эпик", "Описание тестового эпика", new ArrayList<>());
        epic2.setId(epic.getId());
        assertEquals(epic2.hashCode(), epic.hashCode(), "Хэш не совпадает");
    }

    @Test
    public void EpicToStringTest() {
        String epicTestString = "ru.practicum.task_tracker.task.Epic{" +
                "id=" + epic.getId() +
                ", name='" + epic.getName() + '\'' +
                ", description='" + epic.getDescription() + '\'' +
                ", status=" + epic.getStatus() +
                ", subtasksForEpic=" + epic.getSubtasksForEpic() +
                '}';
        assertEquals(epicTestString, epic.toString(), "Текстовое представление не совпадает");
    }
}