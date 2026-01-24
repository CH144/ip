package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;
import johndoe.ui.JohnDoeException;

import java.util.OptionalInt;

/**
 * UnmarkCommand can mark an exising task as not done.
 */
public class UnmarkCommand extends Command {
    private OptionalInt opInt;

    /**
     * Constructor for the UnmarkCommand class for printing help.
     */
    public UnmarkCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    /**
     * Constructor for the UnmarkCommand class for unmarking a task.
     */
    public UnmarkCommand(String input) throws JohnDoeException {
        super(false);
        try {
            opInt = OptionalInt.of(Integer.parseInt(input) - 1);
        } catch (NumberFormatException e) {
            throw new JohnDoeException("  Task number does not exist.\n"
                    + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ");
        }
    }

    /**
     * Marks the task as not done, or prints the help.
     */
    public void run(TaskList taskList, Ui ui) throws JohnDoeException {
        if (opInt.isPresent()) {
            taskList.unmarkTask(opInt.getAsInt(), ui);
        } else {
            ui.printUnmarkHelp();
        }
    }
}
