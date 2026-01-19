import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

class DeadlineCommand extends Command {
    private Optional<Task> opTask;

    public DeadlineCommand() {
        super(false);
        opTask = Optional.empty();
    }

    public DeadlineCommand(String input) {
        super(false);

        String s = "  Enter 'deadline' for more help.\n\n> ";

        String[] tokens = input.split("\\s+");

        for (String token : tokens) {
            if (token != null && !token.equals("/by") && token.contains("/by")) {
                throw new IllegalArgumentException(
                        "  There should be spaces before and after '/by'.\n" + s);
            }
        }

        if (Collections.frequency(Arrays.asList(tokens), "/by") != 1) {
            throw new IllegalArgumentException("  Exactly one '/by' must be used.\n" + s);
        }

        int byIndex = Arrays.asList(tokens).indexOf("/by");

        if (byIndex == 0) {
            throw new IllegalArgumentException("  Please provide a task name.\n" + s);
        }

        if (byIndex == tokens.length - 1) {
            throw new IllegalArgumentException("  Please provide a deadline.\n" + s);
        }

        String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, byIndex));
        String deadline = String.join(" ", Arrays.copyOfRange(tokens, byIndex + 1, tokens.length));
        opTask = Optional.of(new Deadline(taskName, deadline));
    }

    public void run(TaskList tasklist, Ui ui) {
        if (opTask.isPresent()) {
            tasklist.addTask(opTask.get(), ui);
        } else {
            ui.printDeadlineHelp();
        }
    }
}
