package bob.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.task.TaskDateTime;

/**
 * Parses and formats task dates and times.
 */
public final class DateTimeParser {
    private static final DateTimeFormatter USER_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/uuuu");
    private static final DateTimeFormatter USER_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd uuuu");
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm");

    private DateTimeParser() {
        // Utility class; do not instantiate.
    }

    /**
     * Parses a user-entered date or date-time.
     *
     * @param text date in {@code d/M/yyyy} format, optionally followed by a time.
     * @return parsed task date and optional time.
     * @throws IllegalArgumentException if the text does not use a supported format.
     */
    public static TaskDateTime parseUserInput(String text) {
        try {
            if (text.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                LocalDate date = LocalDate.parse(text, USER_DATE_FORMAT);
                return new TaskDateTime(date, null);
            }

            LocalDateTime dateTime = LocalDateTime.parse(text, USER_DATE_TIME_FORMAT);

            return new TaskDateTime(dateTime.toLocalDate(), dateTime.toLocalTime());

        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(
                    "Use d/M/yyyy or d/M/yyyy HHmm, such as 2/12/2019 or 2/12/2019 1800.", exception);
        }
    }

    /**
     * Converts a date-time into the format shown to the user.
     *
     * @param value task date-time to format.
     * @return human-readable date or date-time.
     */
    public static String formatForDisplay(TaskDateTime value) {
        if (value.getTime().isEmpty()) {
            return value.getDate().format(DISPLAY_DATE_FORMAT);
        }

        LocalDateTime dateTime = LocalDateTime.of(value.getDate(), value.getTime().get());

        return dateTime.format(DISPLAY_DATE_TIME_FORMAT);
    }

    /**
     * Converts a date-time into the format used in storage.
     *
     * @param value task date-time to format.
     * @return ISO date or date-time representation.
     */
    public static String formatForStorage(TaskDateTime value) {
        if (value.getTime().isEmpty()) {
            return value.getDate().toString();
        }

        return LocalDateTime.of(value.getDate(), value.getTime().get()).toString();
    }

    /**
     * Reconstructs a date-time from storage.
     *
     * @param text stored ISO date or date-time.
     * @return reconstructed task date and optional time.
     * @throws IllegalArgumentException if the stored value is invalid.
     */
    public static TaskDateTime parseStorage(String text) {
        try {
            if (text.contains("T")) {
                LocalDateTime dateTime = LocalDateTime.parse(text);
                return new TaskDateTime(dateTime.toLocalDate(), dateTime.toLocalTime());
            }

            return new TaskDateTime(LocalDate.parse(text), null);

        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Invalid stored date-time: " + text, exception);
        }
    }
}
