package johndoe.task;

public class Task {
    private String taskName;
    private boolean isDone;

    protected Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;    
    }

    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns if the task name contains the substring.
     */
    public boolean taskNameContains(String s) {
        return taskName.contains(s);
    }

    public String toFileEntry() {
        return String.format("%s | %s",
                isDone ? '1' : '0',
                taskName);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? 'X' : ' ',
                taskName);
    }
}
