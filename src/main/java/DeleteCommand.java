import java.util.OptionalInt;

class DeleteCommand extends Command {
    private OptionalInt opInt;

    public DeleteCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    public DeleteCommand(String input) {
        super(false);
        opInt = OptionalInt.of(Integer.parseInt(input) - 1);
    }

    public void run(TaskList taskList, Ui ui) {
        if (opInt.isPresent()) {
            taskList.deleteTask(opInt.getAsInt(), ui);
        } else {
            ui.printDeleteHelp();
        }
    }
}
