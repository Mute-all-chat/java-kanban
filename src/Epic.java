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
    }

    //Обновляем статус эпика в зависимости от состяония сабтаски
public Status updateEpicStatus() {
    Status epicStatus = Status.IN_PROGRESS;
    ArrayList<Subtask> epicSubtasks = getSubtasksForEpic();
    if (epicSubtasks.isEmpty()) {
        epicStatus = Status.NEW;
    } else {
        int countNewStatus = 0;
        int countDoneStatus = 0;
        for (Subtask subtask : epicSubtasks) {
            if (subtask.getStatus() == Status.DONE) {
                countDoneStatus++;
            }
            if (subtask.getStatus() == Status.NEW) {
                countNewStatus++;
            }
        }
        if (countDoneStatus == epicSubtasks.size()) {
            epicStatus = Status.DONE;
        }
        if (countNewStatus == epicSubtasks.size()) {
            epicStatus = Status.NEW;
        }
    }
    return epicStatus;
}

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subtasksForEpic=" + subtasksForEpic +
                '}';
    }
}
