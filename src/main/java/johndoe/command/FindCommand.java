package johndoe.command;

import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.util.Optional;

/**
 * FindCommand can list all the recorded tasks that have the keywords.
 */
public class FindCommand extends Command {
    private Optional<String> opString;

    /**
     * Constructor for the FindCommand class for print help.
     */
    public FindCommand() {
        super(false);
        opString = Optional.empty();
    }

    /**
     * Constructor for the FindCommand class for finding.
     */
    public FindCommand(String keyWords) {
        super(false);
        opString = Optional.of(keyWords);
    }

    /**
     * Prints all the recorded tasks, or the help.
     */
    public void run(TaskList taskList, Ui ui) {
        if (opString.isPresent()) {
            taskList.findTasks(opString.get(), ui);
        } else {
            ui.printFindHelp();
        }
    }
}
