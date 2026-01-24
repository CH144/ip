package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.util.Optional;

/**
 * Represents the {@code Command} to output all {@code Task} containing keywords.
 */
public class FindCommand extends Command {
    private Optional<String> opString;

    /**
     * Creates a new {@code FindCommand} that will print help.
     */
    public FindCommand() {
        super(false);
        opString = Optional.empty();
    }

    /**
     * Creates a new {@code FindCommand} that will look for relevant {@code Task}.
     */
    public FindCommand(String keyWords) {
        super(false);
        opString = Optional.of(keyWords);
    }

    /**
     * Prints all {@code Task} that contain the keywords in the task name, or prints the help.
     */
    public void run(TaskList taskList, Ui ui) {
        if (opString.isPresent()) {
            taskList.findTasks(opString.get(), ui);
        } else {
            ui.printFindHelp();
        }
    }
}
