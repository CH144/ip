package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.JohnDoeException;
import johndoe.ui.Ui;

public abstract class Command {
    private boolean isBye;

    protected Command(boolean isBye) {
        this.isBye = isBye;
    }

    public boolean isBye() {
        return isBye;
    }

    public abstract void run(TaskList taskList, Ui ui) throws JohnDoeException;
}
