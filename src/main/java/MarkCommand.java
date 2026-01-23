import java.util.OptionalInt;

class MarkCommand extends Command {
    private OptionalInt opInt;

    public MarkCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    public MarkCommand(String input) {
        super(false);
        opInt = OptionalInt.of(Integer.parseInt(input) - 1);
    }

    public void run(TaskList taskList, Ui ui) {
        if (opInt.isPresent()) {
            taskList.markTask(opInt.getAsInt(), ui);
        } else {
            ui.printMarkHelp();
        }
    }
}
