package bob.exception;

/** Represents an error caused by invalid input to Bob. */
public class BobException extends Exception {
    public BobException(String message) {
        super(message);
    }
}
