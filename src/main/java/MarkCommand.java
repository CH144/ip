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

    public void run(TaskList tasklist, Ui ui) {
        if (opInt.isPresent()) {
            tasklist.markTask(opInt.getAsInt());
        } else {
            ui.printMarkHelp();
        }
    }
}
