class HelpCommand extends Command {
    public HelpCommand() {
        super(false);
    }

    public void run(TaskList taskList, Ui ui) {
        ui.printHelp();
    }
}
