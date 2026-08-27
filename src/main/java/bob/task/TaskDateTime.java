package bob.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Represents a task date with an optional time of day.
 */
public class TaskDateTime {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a task date-time with the specified date and optional time.
     */
    public TaskDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public Optional<LocalTime> getTime() {
        return Optional.ofNullable(time);
    }
}
