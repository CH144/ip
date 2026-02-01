package johndoe.command;

import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;

/**
 * Represents the {@code Command} to output all {@code Task}.
 */
public class ListCommand extends Command {
    /**
     * Returns all {@code Task}.
     */
    public String run(TaskList taskList, Storage storage) {
        return taskList.getTasks();
    }
}
