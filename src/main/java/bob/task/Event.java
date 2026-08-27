package bob.task;

import bob.parser.DateTimeParser;

/**
 * Represents a task that takes place over a date or time range.
 */
public class Event extends Task {
    private final TaskDateTime from;
    private final TaskDateTime to;

    /**
     * Creates an event task.
     *
     * @param description description of the event
     * @param from start date and optional time
     * @param to end date and optional time
     */
    public Event(String description, TaskDateTime from, TaskDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a display representation that includes the event range.
     *
     * @return formatted event
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + DateTimeParser.formatForDisplay(from) + " to: "
                                        + DateTimeParser.formatForDisplay(to) + ")";
    }

    /**
     * Returns the start of the event.
     *
     * @return start date-time
     */
    public TaskDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end of the event.
     *
     * @return end date-time
     */
    public TaskDateTime getTo() {
        return to;
    }
}
