package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to output command options.
 */
public class HelpCommand extends Command {
    /**
     * Creates a new {@code HelpCommand}.
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * Prints all the available command options.
     */
    public void run(TaskList taskList, Ui ui) {
        ui.printHelp();
    }
}
