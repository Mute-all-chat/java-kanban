package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;

public interface HistoryManager {
    void add(Task task);

    ArrayList<Task> getHistory();
}