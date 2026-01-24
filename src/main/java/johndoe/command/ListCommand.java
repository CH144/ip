package johndoe.command;

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
     * Prints all {@code Task}.
     */
    public void run(TaskList taskList, Ui ui) {
        taskList.printTasks(ui);
    }
}
