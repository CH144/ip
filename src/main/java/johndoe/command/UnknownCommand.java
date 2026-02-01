package johndoe.command;

import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to output more help.
 */
public class UnknownCommand extends Command {
    /**
     * Returns more help.
     */
    public String run(TaskList taskList, Storage storage) {
        return Ui.getUnknownCommandHelp();
    }
}
