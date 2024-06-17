package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    ArrayList<Task> taskHistory;

    public InMemoryHistoryManager() {
        this.taskHistory = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        taskHistory.add(task);
        if (taskHistory.size() == 11) {
            taskHistory.remove(0);
            //IDEA предлагает заменить метод remove (0) на метод removeFirst(), что выглядит лаконичнее
            // но я натыкался в пачке на тред про то, что ревьюер усомнился в существовании таких методов, поэтому
            // решил сделать в таком формате
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return taskHistory;
    }
}