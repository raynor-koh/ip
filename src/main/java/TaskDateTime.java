import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class TaskDateTime {

    private final LocalDate date;
    private final LocalTime time;

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
