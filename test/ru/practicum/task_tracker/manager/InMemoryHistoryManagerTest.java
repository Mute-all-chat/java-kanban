package ru.practicum.task_tracker.manager;

import org.junit.jupiter.api.*;
import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {
    private static HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void add() {
        Task task = new Task("Тестовая задача", "Описание тестовой задачи", Status.NEW);
        historyManager.add(task);
        final ArrayList<Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(1, history.size(), "Задача не была добавлена в историю.");
    }
}