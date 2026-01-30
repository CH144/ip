package johndoe.command;

import johndoe.exception.JohnDoeException;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Handles method calls to {@code TaskList} and {@code Ui} according to child type.
 */
public abstract class Command {
    private boolean isBye;

    protected Command(boolean isBye) {
        this.isBye = isBye;
    }

    /**
     * Returns if user requested to terminate the app.
     */
    public boolean isBye() {
        return isBye;
    }

    /**
     * Runs methods from {@code TaskList} or {@code Ui} or both according to the child type.
     *
     * @return Corresponding message after running the methods.
     * @throws JohnDoeException If user input format was invalid.
     */
    public abstract String run(TaskList taskList, Ui ui) throws JohnDoeException;
}
