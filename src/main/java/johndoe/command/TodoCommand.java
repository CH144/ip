package johndoe.command;

import johndoe.task.Todo;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.util.Arrays;
import java.util.Optional;

/**
 * TodoCommand can add a todo task to the records.
 */
public class TodoCommand extends Command {
    private Optional<Todo> opTask;

    /**
     * Constructor for the Todo class for printing help.
     */
    public TodoCommand() {
        super(false);
        opTask = Optional.empty();
    }

    /**
     * Constructor for the Todo class for adding a todo.
     */
    public TodoCommand(String input) {
        super(false);
        String[] tokens = input.split("\\s+");
        String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, tokens.length));
        opTask = Optional.of(new Todo(taskName));
    }

    /**
     * Adds a todo, or prints the help.
     */
    public void run(TaskList taskList, Ui ui) {
        if (opTask.isPresent()) {
            taskList.addTask(opTask.get(), ui);
        } else {
            ui.printTodoHelp();
        }
    }
}
