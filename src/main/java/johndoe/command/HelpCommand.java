package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * HelpCommand can print all the available commands.
 */
public class HelpCommand extends Command {
    /**
     * Constructor for the HelpCommand class.
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * Prints all the available commands.
     */
    public void run(TaskList taskList, Ui ui) {
        ui.printHelp();
    }
}
