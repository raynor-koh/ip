package bob.exception;

/** Represents an error caused by invalid input to Bob. */
public class BobException extends Exception {
    /**
     * Creates an exception with a user-facing explanation.
     *
     * @param message explanation of the invalid input or operation
     */
    public BobException(String message) {
        super(message);
    }
}
