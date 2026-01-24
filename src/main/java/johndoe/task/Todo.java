package johndoe.task;

/**
 * Todo is a basic task with no timings associated.
 */
public class Todo extends Task {
    /**
     * Constructor of the Todo class.
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
