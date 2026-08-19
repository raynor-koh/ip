/**
 * Stores tasks entered by the user during the current chatbot run.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;

    private final Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    public void add(Task task) throws BobException {
        if (taskCount == MAX_TASKS) {
            throw new BobException("Your task list is full. Remove a task before adding another one.");
        }
        tasks[taskCount] = task;
        taskCount++;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public Task get(int index) throws BobException {
        if (index < 0 || index >= taskCount) {
            throw new BobException("That task number does not exist. Use 'list' to see the available tasks.");
        }
        return tasks[index];
    }

    public int getTaskCount() {
        return taskCount;
    }
}
