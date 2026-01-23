import java.util.OptionalInt;

class UnmarkCommand extends Command {
    private OptionalInt opInt;

    public UnmarkCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    public UnmarkCommand(String input) throws JohnDoeException {
        super(false);
        try {
            opInt = OptionalInt.of(Integer.parseInt(input) - 1);
        } catch (NumberFormatException e) {
            throw new JohnDoeException("  Task number does not exist.\n"
                    + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ");
        }
    }

    public void run(TaskList taskList, Ui ui) throws JohnDoeException {
        if (opInt.isPresent()) {
            taskList.unmarkTask(opInt.getAsInt(), ui);
        } else {
            ui.printUnmarkHelp();
        }
    }
}
