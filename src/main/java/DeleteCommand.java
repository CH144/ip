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

    public void run(TaskList tasklist, Ui ui) {
        if (opInt.isPresent()) {
            tasklist.deleteTask(opInt.getAsInt());
        } else {
            ui.printDeleteHelp();
        }
    }
}
