import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

class DeadlineCommand extends Command {
    private static final String HELP_SUFFIX = "  Enter 'deadline' for more help.\n\n> ";
    private Optional<Task> opTask;

    public DeadlineCommand() {
        super(false);
        opTask = Optional.empty();
    }

    public DeadlineCommand(String input) throws JohnDoeException {
        super(false);

        String[] tokens = input.split("\\s+");

        for (String token : tokens) {
            if (token != null && !token.equals("/by") && token.contains("/by")) {
                throw new JohnDoeException(
                        "  There should be spaces before and after '/by'.\n" + HELP_SUFFIX);
            }
        }

        if (Collections.frequency(Arrays.asList(tokens), "/by") != 1) {
            throw new JohnDoeException("  Exactly one '/by' must be used.\n" + HELP_SUFFIX);
        }

        int byIndex = Arrays.asList(tokens).indexOf("/by");

        if (byIndex == 0) {
            throw new JohnDoeException("  Please provide a task name.\n" + HELP_SUFFIX);
        }

        if (byIndex == tokens.length - 1) {
            throw new JohnDoeException("  Please provide a deadline.\n" + HELP_SUFFIX);
        }

        String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, byIndex));
        String deadline = String.join(" ", Arrays.copyOfRange(tokens, byIndex + 1, tokens.length));
        opTask = Optional.of(new Deadline(taskName, deadline));
    }

    public void run(TaskList taskList, Ui ui) {
        if (opTask.isPresent()) {
            taskList.addTask(opTask.get(), ui);
        } else {
            ui.printDeadlineHelp();
        }
    }
}
