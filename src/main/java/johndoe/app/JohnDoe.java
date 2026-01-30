package johndoe.app;

import johndoe.command.Command;
import johndoe.exception.JohnDoeException;
import johndoe.parser.Parser;
import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the class handling the logic for the {@code JohnDoe} task management app.
 * The {@code JohnDoe} app is able to:
 * add and delete various types of tasks,
 * track task completion,
 * and store the tasklist to a file for future reference.
 */
public class JohnDoe {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new {@code JohnDoe} with the given file path.
     */
    public JohnDoe(String s) {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(s);
    }

    /**
     * Loads the tasks from the file path in {@code Storage}.
     *
     * @return Success or failure message.
     */
    public String loadTasks() {
        return storage.read(taskList, ui);
    }

    /**
     * Interprets and executes user input using {@code Parser} and {@code Command}.
     *
     * @return Output message of the corresponding {@code Command}, or error message.
     */
    public String processUserInput(String userInput) {
        try {
            Command command = Parser.inputToCommand(userInput);
            if (command.isBye()) {
                storage.write(taskList, ui);
            }
            return command.run(taskList, ui);
        } catch (JohnDoeException e) {
            return e.getMessage();
        }
    }
}
