/**
 * Represents an executable user command.
 */
public abstract class Command {

    public abstract void execute(Ui ui, TaskList tasks) throws BobException;

    public boolean isExit() {
        return false;
    }
}
