package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskStatus;

/** Tests the chatbot's command-processing loop and persistence integration. */
class ChatBotTest {
    @TempDir
    private Path tempDirectory;

    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;

    @AfterEach
    void restoreStreams() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    void run_validCommandSequence_executesAndPersistsCommands() throws Exception {
        ByteArrayOutputStream output = configureStreams(
                "todo read book\nmark 1\nlist\nbye\n");
        Path dataFile = tempDirectory.resolve("tasks.txt");
        ChatBot chatBot = new ChatBot(dataFile.toString());

        chatBot.run();

        List<Task> storedTasks = new Storage(dataFile.toString()).load();
        assertEquals(1, storedTasks.size());
        assertEquals("read book", storedTasks.get(0).getDescription());
        assertEquals(TaskStatus.DONE, storedTasks.get(0).getStatus());
        String displayedText = output.toString(StandardCharsets.UTF_8);
        assertTrue(displayedText.contains("added: [T][ ] read book"));
        assertTrue(displayedText.contains("1.[T][X] read book"));
        assertTrue(displayedText.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    void run_invalidCommand_displaysErrorAndContinuesUntilBye() throws BobException {
        ByteArrayOutputStream output = configureStreams("unknown command\nbye\n");
        ChatBot chatBot = new ChatBot(tempDirectory.resolve("tasks.txt").toString());

        chatBot.run();

        String displayedText = output.toString(StandardCharsets.UTF_8);
        assertTrue(displayedText.contains("I couldn't process that: I don't recognise that command."));
        assertTrue(displayedText.contains("Bye. Hope to see you again soon!"));
    }

    private ByteArrayOutputStream configureStreams(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        return output;
    }
}
