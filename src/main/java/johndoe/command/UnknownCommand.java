package johndoe.command;

import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to output more help.
 */
public class UnknownCommand extends Command {
    /**
     * Creates a new {@code UnknownCommand}.
     */
    public UnknownCommand() {
        super(false);
    }

    /**
     * Returns more help.
     */
    public String run(TaskList taskList, Ui ui, Storage storage) {
        return ui.getUnknownCommandHelp();
    }
}
