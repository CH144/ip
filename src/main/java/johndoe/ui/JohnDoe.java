package johndoe.ui;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;
import johndoe.storage.Storage;
import johndoe.parser.Parser;
import johndoe.command.Command;
import johndoe.ui.JohnDoeException;

/**
 * JohnDoe is a task managing app.
 * It is able to add and delete various types of tasks,
 * as well as track task completion,
 * and store the tasklist to a file for future reference.
 */
public class JohnDoe {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private JohnDoe(String s) {
        taskList = new TaskList();
        ui = new Ui();
        storage =  new Storage(s);
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
     * Entry point of the JohnDoe app.
     * Creates a new JohnDoe object then begins taking user input.
     *
     * @param args Optional command line argument to specify the output file.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            new JohnDoe("./" + args[0]).run();
        } else {
            new JohnDoe("./data/tasks.txt").run();
        }
    }
}
