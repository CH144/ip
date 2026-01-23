class Todo extends Task {
    Todo(String taskName) {
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
