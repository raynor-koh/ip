package bob.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import bob.exception.BobException;

/**
 * Stores tasks entered by the user during the current chatbot run.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this(List.of());
    }

    /**
     * Creates a task list containing a copy of the supplied tasks.
     *
     * @param tasks initial tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Creates a task list containing the supplied tasks.
     *
     * @param tasks initial tasks.
     * @return task list containing the supplied tasks.
     */
    public static TaskList of(Task... tasks) {
        return new TaskList(List.of(tasks));
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a read-only view of the tasks.
     *
     * @return unmodifiable task list.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns the task at a zero-based index.
     *
     * @param index zero-based task index.
     * @return task at the index.
     * @throws BobException if the index is outside the list.
     */
    public Task get(int index) throws BobException {
        if (!isValidIndex(index)) {
            throw new BobException("That task number does not exist. Use 'list' to see the available tasks.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns tasks whose descriptions contain the keyword, ignoring letter case.
     *
     * @param keyword text to search for in task descriptions.
     * @return matching tasks in their original list order.
     */
    public List<Task> find(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ROOT);
        return tasks.stream().filter(task -> task.getDescription().toLowerCase(Locale.ROOT)
                .contains(normalizedKeyword)).toList();
    }

    /**
     * Removes and returns the task at a zero-based index.
     *
     * @param index zero-based task index.
     * @return removed task.
     * @throws BobException if the index is outside the list.
     */
    public Task remove(int index) throws BobException {
        if (!isValidIndex(index)) {
            throw new BobException("That task number does not exist. Use 'list' to see the available tasks.");
        }
        return tasks.remove(index);
    }

    /**
     * Checks whether an index identifies a task in the list.
     *
     * @param index zero-based index to check.
     * @return true if the index is valid.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
