class Deadline extends Task {
    private String deadline;

    Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toFileEntry() {
        return String.format("D | %s | %s",
            super.toFileEntry(),
            deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline);
    }
}
