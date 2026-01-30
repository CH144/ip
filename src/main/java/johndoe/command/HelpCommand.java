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
     * Returns all the available command options.
     */
    public String run(TaskList taskList, Ui ui) {
        return ui.getHelp();
    }
}
