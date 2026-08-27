package bob.task;

import bob.parser.DateTimeParser;

/**
 * Represents a task that occurs over a date-time range.
 */
public class Event extends Task {
    private final TaskDateTime from;
    private final TaskDateTime to;

    /**
     * Creates an event with the specified description, start, and end.
     */
    public Event(String description, TaskDateTime from, TaskDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + DateTimeParser.formatForDisplay(from) + " to: "
                + DateTimeParser.formatForDisplay(to) + ")";
    }

    public TaskDateTime getFrom() {
        return from;
    }

    public TaskDateTime getTo() {
        return to;
    }
}
