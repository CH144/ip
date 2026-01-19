class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    public void run(TaskList tasklist, Ui ui) {
        tasklist.printTasks();
    }
}
