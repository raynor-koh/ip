package bob.exception;

/** Represents an error caused by invalid input to Bob. */
public class BobException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the specified user-facing message.
     */
    public BobException(String message) {
        super(message);
    }
}
