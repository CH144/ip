package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.JohnDoeException;
import johndoe.ui.Ui;

/**
 * Command child classes handle the required calls to TaskList and Ui objects.
 */
public abstract class Command {
    private boolean isBye;

    protected Command(boolean isBye) {
        this.isBye = isBye;
    }

    public boolean isBye() {
        return isBye;
    }

    /**
     * Runs methods from TaskList or Ui or both according to the command.
     */
    public abstract void run(TaskList taskList, Ui ui) throws JohnDoeException;
}
