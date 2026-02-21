package johndoe.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a name and completion status.
 */
public class Task {
    public static final DateTimeFormatter INPUT_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final DateTimeFormatter SAVE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private final String taskName;
    private boolean isDone;

    protected Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns if the task name contains the substring.
     */
    public boolean taskNameContains(String s) {
        return taskName.toLowerCase().contains(s.toLowerCase());
    }

    /**
     * Returns the file entry representation of the task.
     */
    public String toFileEntry() {
        return String.format("%s | %s",
                isDone ? '1' : '0',
                taskName);
    }

    /**
     * Returns the live record representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? 'X' : ' ',
                taskName);
    }
}
