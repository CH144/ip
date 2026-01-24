package johndoe.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task with a name, completion status and deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER=
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime deadline;

    /**
     * Creates a new {@code Deadline} with the given name and deadline.
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toFileEntry() {
        return String.format("D | %s | %s",
            super.toFileEntry(),
            deadline.format(FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(FORMATTER));
    }
}
