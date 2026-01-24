package johndoe.command;

import johndoe.task.Task;
import johndoe.task.Todo;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.util.Arrays;
import java.util.Optional;

public class TodoCommand extends Command {
    private Optional<Task> opTask;

    public TodoCommand() {
        super(false);
        opTask = Optional.empty();
    }

    public TodoCommand(String input) {
        super(false);
        String[] tokens = input.split("\\s+");
        String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, tokens.length));
        opTask = Optional.of(new Todo(taskName));
    }

    public void run(TaskList taskList, Ui ui) {
        if (opTask.isPresent()) {
            taskList.addTask(opTask.get(), ui);
        } else {
            ui.printTodoHelp();
        }
    }
}
