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

    public void add(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public Task get(int index) {
        return tasks[index];
    }

    public int getTaskCount() {
        return taskCount;
    }
}
