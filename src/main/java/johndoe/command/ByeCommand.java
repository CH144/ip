package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * ByeCommand can execute the farewell message from Ui.
 */
public class ByeCommand extends Command {
    /**
     * Constructor of the ByeCommand class.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Executes the farewell message from Ui.
     */
    public void run(TaskList taskList, Ui ui) {
        ui.printFarewell();
    }
}
