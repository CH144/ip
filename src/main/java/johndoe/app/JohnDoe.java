package johndoe.app;

import johndoe.command.Command;
import johndoe.exception.JohnDoeException;
import johndoe.parser.Parser;
import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the main class for the {@code JohnDoe} task management app.
 * The {@code JohnDoe} app is able to:
 * add and delete various types of tasks,
 * track task completion,
 * and store the tasklist to a file for future reference.
 */
public class JohnDoe {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private JohnDoe(String s) {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(s);
    }

    private void run() {
        storage.read(taskList, ui);
        ui.printGreeting();

        boolean isBye = false;
        while (!isBye) {
            try {
                String userInput = ui.readUserInput();
                Command command = Parser.inputToCommand(userInput);
                command.run(taskList, ui);
                isBye = command.isBye();
            } catch (JohnDoeException e) {
                ui.printError(e.getMessage());
            }
        }

        storage.write(taskList, ui);
    }

    /**
     * Creates a new {@code JohnDoe} then runs the app.
     *
     * @param args Optional command line argument to specify the output filename.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            new JohnDoe("./" + args[0]).run();
        } else {
            new JohnDoe("./data/tasks.txt").run();
        }
    }
}
