class Task {
    private String taskName;
    private boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    void markAsDone() {
        isDone = true;    
    }

    void markAsNotDone() {
        isDone = false;
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
