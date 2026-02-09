package johndoe.command;

import java.util.OptionalInt;

import johndoe.exception.JohnDoeException;
import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to mark an existing {@code Task} as not done.
 */
public class UnmarkCommand extends Command {
    private final OptionalInt opInt;

    /**
     * Creates a new {@code UnmarkCommand} that will print help.
     */
    public UnmarkCommand() {
        opInt = OptionalInt.empty();
    }

    /**
     * Creates a new {@code UnmarkCommand} that will mark a {@code Task} as not done.
     *
     * @throws JohnDoeException If input is not a number.
     */
    public UnmarkCommand(String input) throws JohnDoeException {
        assert input != null : "Command argument cannot be null.";

        try {
            opInt = OptionalInt.of(Integer.parseInt(input) - 1);
        } catch (NumberFormatException e) {
            throw new JohnDoeException("  Task number does not exist.\n"
                    + "  Enter 'list' to view all tasks and their corresponding number.\n\n");
        }
    }

    /**
     * Marks the {@code Task} as not done using the index, or prints the help for the command.
     *
     * @return Successful unmark or help message.
     * @throws JohnDoeException If the index is out of bounds.
     */
    public String run(TaskList taskList, Storage storage) throws JohnDoeException {
        if (opInt.isPresent()) {
            return taskList.unmarkTask(opInt.getAsInt())
                    + storage.write(taskList);
        } else {
            return Ui.getUnmarkHelp();
        }
    }
}
