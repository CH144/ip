package johndoe.command;

import java.util.OptionalInt;

import johndoe.exception.JohnDoeException;
import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to remove an existing {@code Task}.
 */
public class DeleteCommand extends Command {
    private OptionalInt opInt;

    /**
     * Creates a new {@code DeleteCommand} that will print help.
     */
    public DeleteCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    /**
     * Creates a new {@code DeleteCommand} that will delete a {@code Task}.
     *
     * @throws JohnDoeException If input is not a number.
     */
    public DeleteCommand(String input) throws JohnDoeException {
        super(false);
        try {
            opInt = OptionalInt.of(Integer.parseInt(input) - 1);
        } catch (NumberFormatException e) {
            throw new JohnDoeException("  Task number does not exist.\n"
                    + "  Enter 'list' to view all tasks and their corresponding number.\n\n");
        }
    }

    /**
     * Deletes the {@code Task} using the index, or prints the help for the command.
     *
     * @return Successful delete or help message.
     * @throws JohnDoeException If index is out of bounds.
     */
    public String run(TaskList taskList, Ui ui, Storage storage) throws JohnDoeException {
        if (opInt.isPresent()) {
            return taskList.deleteTask(opInt.getAsInt(), ui);
        } else {
            return ui.getDeleteHelp();
        }
    }
}
