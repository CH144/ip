class HelpCommand extends Command {
    public HelpCommand() {
        super(false);
    }

    public void run(TaskList tasklist, Ui ui) {
        ui.printHelp();
    }
}
