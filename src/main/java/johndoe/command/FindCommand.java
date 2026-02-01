package johndoe.command;

import java.util.Optional;

import johndoe.storage.Storage;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

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
     * Returns all {@code Task} that contain the keywords in the task name, or the help message.
     */
    public String run(TaskList taskList, Ui ui, Storage storage) {
        if (opString.isPresent()) {
            return taskList.findTasks(opString.get(), ui);
        } else {
            return ui.getFindHelp();
        }
    }
}
