package bob.task;

/**
 * Represents the completion state of a task.
 */
public enum TaskStatus {
    DONE("X", "1"), NOT_DONE(" ", "0");

    private final String icon;
    private final String storageCode;

    TaskStatus(String icon, String storageCode) {
        this.icon = icon;
        this.storageCode = storageCode;
    }

    /** Returns the symbol used when displaying this status. */
    public String getIcon() {
        return icon;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public static TaskStatus fromStorageCode(String code) {
        for (TaskStatus status : values()) {
            if (status.storageCode.equals(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknown task status code: " + code);
    }
}
