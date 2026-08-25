import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    static final String FIELD_SEPARATOR = "|";

    /** Creates storage using Bob's default data file. */
    public Storage() {
        this(Path.of("data", "bob.txt").toString());
    }

    /** Creates storage using the supplied data file path. */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Converts a task into its pipe-generated storage representation.
     */
    private String serializeTask(Task task) {
        String type = task.getType().getSymbol();
        String status = task.getStatus().getStorageCode();

        switch (task.getType()) {
        case TODO:
            return String.join(FIELD_SEPARATOR, type, status, task.getDescription());

        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return String.join(FIELD_SEPARATOR, type, status, deadline.getDescription(),
                                            DateTimeParser.formatForStorage(deadline.getBy()));

        case EVENT:
            Event event = (Event) task;
            return String.join(FIELD_SEPARATOR, type, status, event.getDescription(),
                                            DateTimeParser.formatForStorage(event.getFrom()),
                                            DateTimeParser.formatForStorage((event.getTo())));

        default:
            throw new IllegalArgumentException("Unsupported task type: " + task.getType());

        }
    }

    /**
     * Saves all tasks, replacing the previous file contents.
     */
    public void save(List<Task> tasks) throws IOException {
        try {
            Files.createDirectories(filePath.getParent());
            List<String> lines = new ArrayList<>();

            for (Task task : tasks) {
                lines.add(serializeTask(task));
            }

            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IOException("Could not save tasks to " + filePath + ".", exception);
        }
    }

    /**
     * Converts one stored record into a task.
     *
     * @throws IOException if the stored record is malformed
     */
    public Task deserializeTask(String[] parts, int lineNumber) throws IOException {
        if (parts.length == 0 || parts[0].isBlank()) {
            throw corruptedFile(lineNumber, "missing task type");
        }

        if (parts.length < 2) {
            throw corruptedFile(lineNumber, "missing task status");
        }

        TaskType type;
        try {
            type = TaskType.fromSymbol(parts[0]);
        } catch (IllegalArgumentException exception) {
            throw corruptedFile(lineNumber, "unknown task type '" + parts[0] + "'");
        }

        TaskStatus status;
        try {
            status = TaskStatus.fromStorageCode(parts[1]);
        } catch (IllegalArgumentException exception) {
            throw corruptedFile(lineNumber, "invalid task status");
        }

        if (parts.length != type.getStorageFieldCount()) {
            throw corruptedFile(lineNumber, "expected " + type.getStorageFieldCount() + " fields but found "
                                            + parts.length);
        }

        Task task;

        switch (type) {
        case TODO:
            task = new ToDo(parts[2]);
            break;

        case DEADLINE:
            try {
                task = new Deadline(parts[2], DateTimeParser.parseStorage(parts[3]));
            } catch (DateTimeParseException exception) {
                throw corruptedFile(lineNumber, "invalid deadline date");
            }
            break;

        case EVENT:
            try {
                task = new Event(parts[2], DateTimeParser.parseStorage(parts[3]),
                                                DateTimeParser.parseStorage(parts[4]));
            } catch (DateTimeParseException exception) {
                throw corruptedFile(lineNumber, "invalid event date");
            }
            break;

        default:
            throw corruptedFile(lineNumber, "unsupported task type '" + type + "'");
        }

        if (status == TaskStatus.DONE) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Loads tasks from the storage file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();

        // A new installation does not have a data file yet.
        if (Files.notExists(filePath)) {
            return tasks;
        }

        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
            String line = lines.get(lineNumber);
            String[] parts = line.split("\\s*\\|\\s*", -1);

            Task task = deserializeTask(parts, lineNumber);
            tasks.add(task);
        }

        return tasks;
    }

    /** Creates a consistent error for malformed storage records. */
    private IOException corruptedFile(int lineNumber, String reason) {
        return new IOException("Could not load saved tasks: corrupted data on line " + (lineNumber + 1) + " (" + reason
                                        + ").");
    }

}
