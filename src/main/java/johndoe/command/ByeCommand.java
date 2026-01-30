package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

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
    public String run(TaskList taskList, Ui ui) {
        return "bye";
    }
}
