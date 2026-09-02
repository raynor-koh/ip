package bob;

/**
 * Represents the semantic type of a chatbot response for presentation purposes.
 */
public enum ResponseType {
    /** Confirms that a task was added. */
    ADD,
    /** Ends the current chatbot session. */
    BYE,
    /** Confirms that a task was deleted. */
    DELETE,
    /** Reports a recoverable command-processing error. */
    ERROR,
    /** Displays tasks matching a search keyword. */
    FIND,
    /** Displays the complete task list. */
    LIST,
    /** Confirms that a task was marked as done. */
    MARK,
    /** Confirms that a task was marked as not done. */
    UNMARK
}
