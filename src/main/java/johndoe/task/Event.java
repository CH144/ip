package johndoe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import johndoe.exception.JohnDoeException;
/**
 * Represents a task with a name, completion status, start and end.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a new {@code Event} with the given name, start and end.
     *
     * @throws JohnDoeException If start is chronologically after end.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime end) throws JohnDoeException {
        super(taskName);
        if (start.isAfter(end)) {
            throw new JohnDoeException("  Start cannot be after end.\n"
                + "Enter 'event' for more help.\n\n> ");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileEntry() {
        return String.format("E | %s | %s ~ %s",
                super.toFileEntry(),
                start.format(FORMATTER),
                end.format(FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                start.format(FORMATTER),
                end.format(FORMATTER));
    }
}
