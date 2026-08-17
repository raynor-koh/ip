/**
 * Stores task descriptions entered by the user during the current chatbot run.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;

    private final String[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new String[MAX_TASKS];
        this.taskCount = 0;
    }

    public void add(String task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public String[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }
}
