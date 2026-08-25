public class Deadline extends Task {
    protected TaskDateTime by;

    public Deadline(String description, TaskDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }

    public TaskDateTime getBy() {
        return by;
    }
}
