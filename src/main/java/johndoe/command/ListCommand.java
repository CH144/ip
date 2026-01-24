package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * ListCommand can list all the recorded tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor for the ListCommand class.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Prints all the recorded tasks.
     */
    public void run(TaskList taskList, Ui ui) {
        taskList.printTasks(ui);
    }
}
