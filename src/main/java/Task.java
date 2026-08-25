/**
 * Represents a task tracked by the chatbot.
 */
public abstract class Task {
    protected String description;
    private TaskStatus status;
    private final TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    /** Returns the kind of this task. */
    public TaskType getType() {
        return type;
    }

    /** Returns the task description */
    public String getDescription() {
        return description;
    }

    /** Returns the current completion state of this task. */
    public TaskStatus getStatus() {
        return status;
    }

    public String getStatusIcon() {
        return status.getIcon();
    }

    public void markAsDone() {
        status = TaskStatus.DONE;
    }

    public void markAsNotDone() {
        status = TaskStatus.NOT_DONE;
    }

    @Override
    public String toString() {
        return "[" + type.getSymbol() + "][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
