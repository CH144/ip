class Event extends Task {
    private String start;
    private String end;

    Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileEntry() {
        return String.format("E | %s | %s ~ %s",
                super.toFileEntry(),
                start,
                end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                start,
                end);
    }
}
