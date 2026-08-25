/**
 * Command that displays all stored tasks.
 */
public class ListCommand extends Command {
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showTasks(tasks.getTasks(), tasks.getTaskCount());
        return false;
    }
}
