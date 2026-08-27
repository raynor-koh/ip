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

    public TaskList() {
        this(List.of());
    }

    /** Creates a task list containing a copy of the supplied tasks. */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public Task get(int index) throws BobException {
        if (!isValidIndex(index)) {
            throw new BobException("That task number does not exist. Use 'list' to see the available tasks.");
        }
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns tasks whose descriptions contain the keyword, ignoring letter case.
     *
     * @param keyword text to search for in task descriptions
     * @return matching tasks in their original list order
     */
    public List<Task> find(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ROOT);
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase(Locale.ROOT).contains(normalizedKeyword))
                .toList();
    }

    public Task remove(int index) throws BobException {
        if (!isValidIndex(index)) {
            throw new BobException("That task number does not exist. Use 'list' to see the available tasks.");
        }
        return tasks.remove(index);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
