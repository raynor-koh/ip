/**
 * Represents the completion state of a task.
 */
public enum TaskStatus {
    DONE("X"),
    NOT_DONE(" ");

    private final String icon;

    TaskStatus(String icon) {
        this.icon = icon;
    }

    /** Returns the symbol used when displaying this status. */
    public String getIcon() {
        return icon;
    }
}
