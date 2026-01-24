package johndoe.command;

import johndoe.exception.JohnDoeException;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.util.OptionalInt;

/**
 * Represents the {@code Command} to mark an existing {@code Task} as not done.
 */
public class UnmarkCommand extends Command {
    private OptionalInt opInt;

    /**
     * Creates a new {@code UnmarkCommand} that will print help.
     */
    public UnmarkCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    /**
     * Creates a new {@code UnmarkCommand} that will mark a {@code Task} as not done.
     *
     * @throws JohnDoeException If input is not a number.
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
     * Marks the {@code Task} as not done using the index, or prints the help for the command.
     *
     * @throws JohnDoeException If the index is out of bounds.
     */
    public void run(TaskList taskList, Ui ui) throws JohnDoeException {
        if (opInt.isPresent()) {
            taskList.unmarkTask(opInt.getAsInt(), ui);
        } else {
            ui.printUnmarkHelp();
        }
    }
}
