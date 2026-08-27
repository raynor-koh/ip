package bob.task;

import bob.parser.DateTimeParser;

/**
 * Represents a task that must be completed by a specific date and optional time.
 */
public class Deadline extends Task {
    protected TaskDateTime by;

    /**
     * Creates a deadline with the specified description and due date.
     */
    public Deadline(String description, TaskDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + DateTimeParser.formatForDisplay(by) + ")";
    }

    public TaskDateTime getBy() {
        return by;
    }
}
