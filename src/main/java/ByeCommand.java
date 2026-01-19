class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    public void run(TaskList tasklist, Ui ui) {
        ui.printFarewell();
    }
}
