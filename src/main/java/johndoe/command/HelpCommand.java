package johndoe.command;

import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to output command options.
 */
public class HelpCommand extends Command {
    /**
     * Returns all the available command options.
     */
    public String run(TaskList taskList, Storage storage) {
        return Ui.getHelp();
    }
}
