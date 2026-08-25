public class Event extends Task {
    private final TaskDateTime from;
    private final TaskDateTime to;

    public Event(String description, TaskDateTime from, TaskDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

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
