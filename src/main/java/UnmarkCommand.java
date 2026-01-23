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

    public void run(TaskList taskList, Ui ui) {
        if (opInt.isPresent()) {
            taskList.unmarkTask(opInt.getAsInt(), ui);
        } else {
            ui.printUnmarkHelp();
        }
    }
}
