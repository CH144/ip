package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * UnknownCommand can print further help.
 */
public class UnknownCommand extends Command {
    /**
     * Constructor of the UnknownCommand class.
    public UnknownCommand() {
        super(false);
    }

    /**
     * Prints more help.
     */
    public void run(TaskList taskList, Ui ui) {
        ui.printUnknownCommandHelp();
    }
}
