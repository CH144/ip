class UnknownCommand extends Command {
    public UnknownCommand() {
        super(false);
    }

    public void run(TaskList tasklist, Ui ui) {
        ui.printUnknownCommandHelp();
    }
}
