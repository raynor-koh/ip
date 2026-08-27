package bob.task;

/**
 * Identifies the kind of task stored by the chatbot.
 */
public enum TaskType {
    TODO("T", 3), DEADLINE("D", 4), EVENT("E", 5);

    private final String symbol;
    private final int storageFieldCount;

    TaskType(String symbol, int storageFieldCount) {
        this.symbol = symbol;
        this.storageFieldCount = storageFieldCount;
    }

    /** Returns the symbol used when displaying this task type. */
    public String getSymbol() {
        return symbol;
    }

    public int getStorageFieldCount() {
        return storageFieldCount;
    }

    /**
     * Returns the task type represented by the specified storage symbol.
     */
    public static TaskType fromSymbol(String symbol) {
        for (TaskType type : values()) {
            if (type.symbol.equals(symbol)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Unknown task type symbol: " + symbol);
    }
}
