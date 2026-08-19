/**
 * Represents a task tracked by the chatbot.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private final TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /** Returns the kind of this task. */
    public TaskType getType() {
        return type;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + type.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description;
    }
}
