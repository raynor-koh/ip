public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(task);
        ui.showAdded(task, tasks.getTaskCount());
    }
}
