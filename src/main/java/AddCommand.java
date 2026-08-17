public class AddCommand extends Command {
    private final String task;

    public AddCommand(String task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(task);
        ui.showAdded(task);
    }
}
