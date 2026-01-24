package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to terminate the app.
 */
public class ByeCommand extends Command {
    /**
     * Creates a new {@code ByeCommand}.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Executes the farewell message from {@code Ui}.
     */
    public void run(TaskList taskList, Ui ui) {
        ui.printFarewell();
    }
}
