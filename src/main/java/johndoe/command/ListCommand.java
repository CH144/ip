package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    public void run(TaskList taskList, Ui ui) {
        taskList.printTasks(ui);
    }
}
