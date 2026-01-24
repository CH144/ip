package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.JohnDoeException;
import johndoe.ui.Ui;

import java.util.OptionalInt;

public class MarkCommand extends Command {
    private OptionalInt opInt;

    public MarkCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    public MarkCommand(String input) throws JohnDoeException {
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
            taskList.markTask(opInt.getAsInt(), ui);
        } else {
            ui.printMarkHelp();
        }
    }
}
