package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bob.exception.BobException;

/** Tests task-list mutation, access, and boundary behavior. */
class TaskListTest {

    @Test
    void constructor_sourceListChanged_taskListUnaffected() {
        List<Task> source = new ArrayList<>();
        source.add(new ToDo("read book"));
        TaskList taskList = new TaskList(source);

        source.clear();

        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void add_validTask_taskAdded() throws BobException {
        TaskList taskList = new TaskList();
        Task task = new ToDo("read book");

        taskList.add(task);

        assertEquals(1, taskList.getTaskCount());
        assertSame(task, taskList.get(0));
    }

    @Test
    void get_validIndex_returnsTask() throws BobException {
        Task firstTask = new ToDo("first task");
        Task secondTask = new ToDo("second task");
        TaskList taskList = new TaskList(List.of(firstTask, secondTask));

        assertSame(firstTask, taskList.get(0));
        assertSame(secondTask, taskList.get(1));
    }

    @Test
    void get_invalidIndex_bobExceptionThrown() {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));

        assertThrows(BobException.class, () -> taskList.get(-1));
        assertThrows(BobException.class, () -> taskList.get(1));
    }

    @Test
    void remove_validIndex_removesAndReturnsTask() throws BobException {
        Task firstTask = new ToDo("first task");
        Task secondTask = new ToDo("second task");
        TaskList taskList = new TaskList(List.of(firstTask, secondTask));

        Task removedTask = taskList.remove(0);

        assertSame(firstTask, removedTask);
        assertEquals(1, taskList.getTaskCount());
        assertSame(secondTask, taskList.get(0));
    }

    @Test
    void remove_invalidIndex_bobExceptionThrownAndListUnchanged() {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));

        assertThrows(BobException.class, () -> taskList.remove(-1));
        assertThrows(BobException.class, () -> taskList.remove(1));
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void getTasks_modifyReturnedList_unsupportedOperationExceptionThrown() {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));

        assertThrows(UnsupportedOperationException.class,
                () -> taskList.getTasks().add(new ToDo("write report")));
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void find_keywordWithDifferentCase_returnsMatchingTasksInOriginalOrder() {
        Task firstMatch = new ToDo("read book");
        Task nonMatch = new ToDo("write report");
        Task secondMatch = new ToDo("return BOOK");
        TaskList taskList = new TaskList(List.of(firstMatch, nonMatch, secondMatch));

        List<Task> matchingTasks = taskList.find("book");

        assertEquals(List.of(firstMatch, secondMatch), matchingTasks);
    }

    @Test
    void find_noMatchingDescription_returnsEmptyList() {
        TaskList taskList = new TaskList(List.of(new ToDo("write report")));

        assertEquals(List.of(), taskList.find("book"));
    }
}
