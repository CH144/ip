package johndoe.command;

import johndoe.exception.JohnDoeException;
import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;

/**
 * Handles method calls to {@code TaskList} and {@code Ui} according to child type.
 */
public abstract class Command {
    /**
     * Runs methods from {@code TaskList} or {@code Ui} or both according to the child type.
     *
     * @return Corresponding message after running the methods.
     * @throws JohnDoeException If user input format was invalid.
     */
    public abstract String run(TaskList taskList, Storage storage) throws JohnDoeException;
}
