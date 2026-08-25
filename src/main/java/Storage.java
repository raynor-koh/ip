import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath = Path.of("data", "bob.txt");

    /**
     * Saves all tasks, replacing the previous file contents.
     */
    public void save(TaskList taskList) throws IOException {
        Files.createDirectories(filePath.getParent());
        List<String> lines = new ArrayList<>();

        for (Task task : taskList.getTasks()) {
            String status = task.getStatus() == TaskStatus.DONE ? "1" : "0";

            switch (task.getType()) {
            case TODO:
                lines.add(String.join("|", "T", status, task.getDescription()));
                break;

            case DEADLINE:
                Deadline deadline = (Deadline) task;
                lines.add(String.join("|", "D", status, deadline.getDescription(), deadline.getBy()));
                break;

            case EVENT:
                Event event = (Event) task;
                lines.add(String.join("|", "E", status, event.getDescription(), event.getFrom(), event.getTo()));
                break;
            }
        }

        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }

    /**
     * Loads tasks from the storage file.
     */
    public TaskList load() throws IOException {
        TaskList taskList = new TaskList();
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] parts = line.split("\\s*\\|\\s*", -1);

            String type = parts[0];
            boolean isDone = parts[1].equals("1");

            Task task;

            switch (type) {
            case "T":
                task = new ToDo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
            default:
                throw new IOException("Unknown task type: " + type);
            }

            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }

        return taskList;
    }
}
