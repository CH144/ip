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

    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? 'X' : ' ',
                taskName);
    }
}
