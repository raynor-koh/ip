import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public String toStorageString() {
        if (time == null) {
            return date.toString(); // 2019-12-02
        }

        return LocalDateTime.of(date, time).toString(); // 2019-12-02T18:00
    }

    public static TaskDateTime fromStorageString(String text) {
        if (text.contains("T")) {
            LocalDateTime dateTime = LocalDateTime.parse(text);
            return new TaskDateTime(dateTime.toLocalDate(), dateTime.toLocalTime());
        }

        LocalDate date = LocalDate.parse(text);
        return new TaskDateTime(date, null);
    }
}
