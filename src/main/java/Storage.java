import java.io.IOException;
import java.nio.file.Files;
import  java.nio.file.Path;
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

        for (Task task: taskList.getTasks()) {
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

        Files.write(filePath, lines);
    }

}
