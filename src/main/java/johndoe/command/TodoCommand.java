package johndoe.command;

import java.util.Arrays;
import java.util.Optional;

import johndoe.task.Todo;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to add a {@code Todo}.
 */
public class TodoCommand extends Command {
    private Optional<Todo> opTask;

    /**
     * Creates a new {@code TodoCommand} that will print help.
     */
    public TodoCommand() {
        super(false);
        opTask = Optional.empty();
    }

    /**
     * Creates a new {@code TodoCommand} that will add a {@code Todo}.
     */
    public TodoCommand(String input) {
        super(false);
        String[] tokens = input.split("\\s+");
        String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, tokens.length));
        opTask = Optional.of(new Todo(taskName));
    }

    /**
     * Adds a {@code Todo}, or prints the help for the command.
     */
    public void run(TaskList taskList, Ui ui) {
        if (opTask.isPresent()) {
            taskList.addTask(opTask.get(), ui);
        } else {
            ui.printTodoHelp();
        }
    }
}
