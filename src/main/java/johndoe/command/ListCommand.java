package johndoe.command;

import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to output all {@code Task}.
 */
public class ListCommand extends Command {
    /**
     * Create a new {@code ListCommand}.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Returns all {@code Task}.
     */
    public String run(TaskList taskList, Ui ui, Storage storage) {
        return taskList.getTasks(ui);
    }
}
