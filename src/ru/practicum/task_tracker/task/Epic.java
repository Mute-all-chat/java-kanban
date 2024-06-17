package ru.practicum.task_tracker.task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasksForEpic = new ArrayList<>();

    public Epic(String name, String description, ArrayList<Subtask> subtasksForEpic) {
        super(name, description);
        this.subtasksForEpic = subtasksForEpic;
        setStatus(updateEpicStatus());
    }

    public ArrayList<Subtask> getSubtasksForEpic() {
        return subtasksForEpic;
    }

    public void setSubtasksForEpic(ArrayList<Subtask> subtasksForEpic) {
        this.subtasksForEpic = subtasksForEpic;
        this.updateEpicStatus();
    }

    //Обновляем статус эпика в зависимости от состяония сабтаски
    public Status updateEpicStatus() {
        Status epicStatus = Status.IN_PROGRESS;
        ArrayList<Subtask> epicSubtasks = getSubtasksForEpic();
        if (epicSubtasks.isEmpty()) {
            epicStatus = Status.NEW;
        } else {
            int countOfNewStatus = 0;
            int countOfDoneStatus = 0;
            for (Subtask subtask : epicSubtasks) {
                if (subtask.getStatus() == Status.DONE) {
                    countOfDoneStatus++;
                }
                if (subtask.getStatus() == Status.NEW) {
                    countOfNewStatus++;
                }
            }
            if (countOfDoneStatus == epicSubtasks.size()) {
                epicStatus = Status.DONE;
            }
            if (countOfNewStatus == epicSubtasks.size()) {
                epicStatus = Status.NEW;
            }
        }
        return epicStatus;
    }

    @Override
    public String toString() {
        return "ru.practicum.task_tracker.task.Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subtasksForEpic=" + subtasksForEpic +
                '}';
    }
}