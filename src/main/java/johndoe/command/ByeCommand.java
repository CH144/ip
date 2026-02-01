package johndoe.command;

import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;

/**
 * Represents the {@code Command} to terminate the app.
 */
public class ByeCommand extends Command {
    /**
     * Creates a new {@code ByeCommand}.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Returns 'bye' to close the application.
     */
    public String run(TaskList taskList, Storage storage) {
        return "bye";
    }
}
