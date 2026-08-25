import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskDateTime {
    private static final DateTimeFormatter DATE_OUTPUT = DateTimeFormatter.ofPattern("MMM dd uuuu");

    private static final DateTimeFormatter TIME_OUTPUT = DateTimeFormatter.ofPattern("HH:mm");

    private final LocalDate date;
    private final LocalTime time; // null if only a date was provided

    public TaskDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        String result = date.format(DATE_OUTPUT);
        return time == null ? result : result + " " + time.format(TIME_OUTPUT);
    }
}
