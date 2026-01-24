package johndoe.task;

/**
 * Represents a task with a name and completion status.
 */
public class Todo extends Task {
    /**
     * Creates a new {@code Todo} with the given name.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toFileEntry() {
        return "T | " + super.toFileEntry();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
