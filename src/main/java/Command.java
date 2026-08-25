/**
 * Represents an executable user command.
 */
public abstract class Command {

    public abstract boolean execute(Ui ui, TaskList tasks) throws BobException;

    public boolean isExit() {
        return false;
    }
}
