package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;

public interface HistoryManager {
   int HISTORY_SIZE_LIMIT = 10;
   int HISTORY_FIRST_ELEMENT_INDEX = 0;

    void add(Task task);

    ArrayList<Task> getHistory();
}