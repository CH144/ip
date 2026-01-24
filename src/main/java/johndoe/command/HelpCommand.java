package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(false);
    }

    public void run(TaskList taskList, Ui ui) {
        ui.printHelp();
    }
}
