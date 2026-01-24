package johndoe.ui;

import johndoe.command.Command;
import johndoe.parser.Parser;
import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.JohnDoeException;
import johndoe.ui.Ui;

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

    public static void main(String[] args) {
        if (args.length > 0) {
            new JohnDoe("./" + args[0]).run();
        } else {
            new JohnDoe("./data/tasks.txt").run();
        }
    }
}
