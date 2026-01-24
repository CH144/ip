package johndoe.command;

import johndoe.exception.JohnDoeException;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.util.OptionalInt;

/**
 * MarkCommand can mark an existing task as done.
 */
public class MarkCommand extends Command {
    private OptionalInt opInt;

    /**
     * Constructor for the MarkCommand for printing help.
     */
    public MarkCommand() {
        super(false);
        opInt = OptionalInt.empty();
    }

    /**
     * Constructor for the MarkCommand for marking a task.
     */
    public MarkCommand(String input) throws JohnDoeException {
        super(false);
        try {
            opInt = OptionalInt.of(Integer.parseInt(input) - 1);
        } catch (NumberFormatException e) {
            throw new JohnDoeException("  Task number does not exist.\n"
                    + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ");
        }
    }

    /**
     * Marks the task as done, or prints the help.
     */
    public void run(TaskList taskList, Ui ui) throws JohnDoeException {
        if (opInt.isPresent()) {
            taskList.markTask(opInt.getAsInt(), ui);
        } else {
            ui.printMarkHelp();
        }
    }
}
