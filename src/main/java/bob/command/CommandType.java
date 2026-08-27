package bob.command;

/**
 * Represents a command that can be entered by the user.
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String keyword;

    CommandType(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * Returns the command type matching the specified keyword, or {@code null} if none matches.
     */
    public static CommandType fromKeyword(String keyword) {
        for (CommandType commandType : values()) {
            if (commandType.keyword.equalsIgnoreCase(keyword)) {
                return commandType;
            }
        }
        return null;
    }
}
