package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    public void run(TaskList taskList, Ui ui) {
        ui.printFarewell();
    }
}
