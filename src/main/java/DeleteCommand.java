import java.util.OptionalInt;

class DeleteCommand extends Command {
    private OptionalInt opInt;

    public DeleteCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    public DeleteCommand(String input) throws JohnDoeException {
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
            taskList.deleteTask(opInt.getAsInt(), ui);
        } else {
            ui.printDeleteHelp();
        }
    }
}
