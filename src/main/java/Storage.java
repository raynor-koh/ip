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
        try {
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
        } catch (IOException exception) {
            throw new IOException("Could not save tasks to " + filePath + ".", exception);
        }
    }

    /**
     * Loads tasks from the storage file.
     */
    public TaskList load() throws IOException {
        TaskList taskList = new TaskList();

        // A new installation does not have a data file yet.
        if (Files.notExists(filePath)) {
            return taskList;
        }

        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
            String line = lines.get(lineNumber);
            String[] parts = line.split("\\s*\\|\\s*", -1);

            if (parts.length == 0 || parts[0].isBlank()) {
                throw corruptedFile(lineNumber, "missing task type");
            }

            String type = parts[0];
            if (parts.length < 2 || !(parts[1].equals("0") || parts[1].equals("1"))) {
                throw corruptedFile(lineNumber, "invalid task status");
            }
            boolean isDone = parts[1].equals("1");

            Task task;

            switch (type) {
            case "T":
                requirePartCount(parts, 3, lineNumber);
                task = new ToDo(parts[2]);
                break;
            case "D":
                requirePartCount(parts, 4, lineNumber);
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                requirePartCount(parts, 5, lineNumber);
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw corruptedFile(lineNumber, "unknown task type '" + type + "'");
            }

            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }

        return taskList;
    }

    /** Ensures that a stored task has exactly the fields required by its type. */
    private void requirePartCount(String[] parts, int expectedCount, int lineNumber)
            throws IOException {
        if (parts.length != expectedCount) {
            throw corruptedFile(lineNumber, "expected " + expectedCount + " fields but found "
                    + parts.length);
        }
    }

    /** Creates a consistent error for malformed storage records. */
    private IOException corruptedFile(int lineNumber, String reason) {
        return new IOException("Could not load saved tasks: corrupted data on line "
                + (lineNumber + 1) + " (" + reason + ").");
    }
}
