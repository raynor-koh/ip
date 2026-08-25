import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores tasks entered by the user during the current chatbot run.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
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
