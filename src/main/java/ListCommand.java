class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    public void run(TaskList taskList, Ui ui) {
        taskList.printTasks(ui);
    }
}
