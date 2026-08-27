package bob.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bob.exception.BobException;

/**
 * Stores tasks entered by the user during the current chatbot run.
 */
public class TaskList {
    private final List<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this(List.of());
    }

    /** Creates a task list containing a copy of the supplied tasks. */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** Adds a task to this list. */
    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns the task at the specified zero-based index.
     */
    public Task get(int index) throws BobException {
        if (!isValidIndex(index)) {
            throw new BobException(
                    "That task number does not exist. Use 'list' to see the available tasks.");
        }
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Removes and returns the task at the specified zero-based index.
     */
    public Task remove(int index) throws BobException {
        if (!isValidIndex(index)) {
            throw new BobException(
                    "That task number does not exist. Use 'list' to see the available tasks.");
        }
        return tasks.remove(index);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
