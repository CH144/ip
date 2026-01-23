class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    public void run(TaskList taskList, Ui ui) {
        ui.printFarewell();
    }
}
