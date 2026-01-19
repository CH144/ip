import java.util.OptionalInt;

class UnmarkCommand extends Command {
    private OptionalInt opInt;

    public UnmarkCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    public UnmarkCommand(String input) {
        super(false);
        opInt = OptionalInt.of(Integer.parseInt(input) - 1);
    }

    public void run(TaskList tasklist, Ui ui) {
        if (opInt.isPresent()) {
            tasklist.unmarkTask(opInt.getAsInt());
        } else {
            ui.printUnmarkHelp();
        }
    }
}
