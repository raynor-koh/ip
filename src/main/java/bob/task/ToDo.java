package bob.task;

/**
 * Represents a task without an associated date or time.
 */
public class ToDo extends Task {
    /**
     * Creates a todo with the specified description.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }
}
