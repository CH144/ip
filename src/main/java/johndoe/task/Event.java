package johndoe.task;

import johndoe.exception.JohnDoeException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Event is a task that has a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor of the Event class.
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
