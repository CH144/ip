package johndoe.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Deadline is a task with a deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER=
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime deadline;

    /**
     * Constructor of the Deadline class.
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
