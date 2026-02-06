package johndoe.task;

import java.time.LocalDateTime;

import johndoe.exception.JohnDoeException;

/**
 * Represents a task with a name, completion status, start and end.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates a new {@code Event} with the given name, start and end.
     *
     * @throws JohnDoeException If start is chronologically after end.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime end) throws JohnDoeException {
        super(taskName);
        if (start.isAfter(end)) {
            throw new JohnDoeException("  Start cannot be after end.\n"
                + "Enter 'event' for more help.\n\n");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileEntry() {
        return String.format("E | %s | %s ~ %s",
                super.toFileEntry(),
                start.format(SAVE_TIME_FORMAT),
                end.format(SAVE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                start.format(SAVE_TIME_FORMAT),
                end.format(SAVE_TIME_FORMAT));
    }
}
