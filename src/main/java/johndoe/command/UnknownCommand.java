package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super(false);
    }

    public void run(TaskList taskList, Ui ui) {
        ui.printUnknownCommandHelp();
    }
}
