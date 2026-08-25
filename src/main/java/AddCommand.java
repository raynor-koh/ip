import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException, IOException {
        tasks.add(task);
        ui.showAdded(task, tasks.getTaskCount());
        storage.save(tasks.getTasks());
    }
}
