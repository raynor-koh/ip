import java.io.IOException;

/**
 * Represents an executable user command.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobException, IOException;

    public boolean isExit() {
        return false;
    }
}
