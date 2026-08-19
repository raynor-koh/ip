/**
 * Identifies the kind of task stored by the chatbot.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /** Returns the symbol used when displaying this task type. */
    public String getSymbol() {
        return symbol;
    }
}
