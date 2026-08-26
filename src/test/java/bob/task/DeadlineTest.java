package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/** Tests the user-facing representation of deadline tasks. */
class DeadlineTest {

    @Test
    void toString_dateOnly_includesFormattedDueDate() {
        Deadline deadline = new Deadline("submit report",
                new TaskDateTime(LocalDate.of(2019, 12, 2), null));

        assertEquals("[D][ ] submit report (by: Dec 02 2019)", deadline.toString());
    }

    @Test
    void toString_dateAndTime_includesFormattedDueDateAndTime() {
        Deadline deadline = new Deadline("submit report",
                new TaskDateTime(LocalDate.of(2019, 12, 2), LocalTime.of(18, 0)));

        assertEquals("[D][ ] submit report (by: Dec 02 2019 18:00)", deadline.toString());
    }
}
