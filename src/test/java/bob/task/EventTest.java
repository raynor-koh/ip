package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/** Tests the user-facing representation of event tasks. */
class EventTest {

    @Test
    void toString_dateAndTimeRange_includesFormattedStartAndEnd() {
        Event event = new Event("project meeting",
                new TaskDateTime(LocalDate.of(2019, 12, 2), LocalTime.of(18, 0)),
                new TaskDateTime(LocalDate.of(2019, 12, 2), LocalTime.of(19, 30)));

        assertEquals("[E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 02 2019 19:30)",
                event.toString());
    }

    @Test
    void toString_dateOnlyRange_includesFormattedStartAndEnd() {
        Event event = new Event("camp",
                new TaskDateTime(LocalDate.of(2019, 12, 2), null),
                new TaskDateTime(LocalDate.of(2019, 12, 4), null));

        assertEquals("[E][ ] camp (from: Dec 02 2019 to: Dec 04 2019)", event.toString());
    }
}
