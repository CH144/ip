package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.JohnDoeException;
import johndoe.ui.Ui;

import java.util.OptionalInt;

/**
 * DeleteCommand can remove an existing task from the records.
 */
public class DeleteCommand extends Command {
    private OptionalInt opInt;

    /**
     * Constructor for the DeleteCommand class for printing help.
     */
    public DeleteCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    /**
     * Constructor for the DeleteCommand class for deleting a task.
     */
    public DeleteCommand(String input) throws JohnDoeException {
        super(false);
        try {
            opInt = OptionalInt.of(Integer.parseInt(input) - 1);
        } catch (NumberFormatException e) {
            throw new JohnDoeException("  Task number does not exist.\n"
                    + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ");
        }
    }

    /**
     * Deletes the task, or prints the help for the command.
     */
    public void run(TaskList taskList, Ui ui) throws JohnDoeException {
        if (opInt.isPresent()) {
            taskList.deleteTask(opInt.getAsInt(), ui);
        } else {
            ui.printDeleteHelp();
        }
    }
}
