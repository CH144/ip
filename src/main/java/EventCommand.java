import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

class EventCommand extends Command {
    private static final String HELP_SUFFIX = "  Enter 'event' for more help.\n\n> ";
    private Optional<Task> opTask;

    public EventCommand() {
        super(false);
        opTask = Optional.empty();
    }

    public EventCommand(String input) {
        super(false);

        String[] tokens = input.split("\\s+");

        for (String token : tokens) {
            if ((token != null && !token.equals("/from") && token.contains("/from"))
                    || (token != null && !token.equals("/to") && token.contains("/to"))) {
                throw new IllegalArgumentException(
                        "  There should be spaces before and after '/from' and '/to'.\n" + HELP_SUFFIX);
            }
        }

        if (Collections.frequency(Arrays.asList(tokens), "/from") != 1
                || Collections.frequency(Arrays.asList(tokens), "/to") != 1) {
            throw new IllegalArgumentException(
                        "  Exactly one '/from' and one '/to' must be used.\n" + HELP_SUFFIX);
        }

        int fromIndex = Arrays.asList(tokens).indexOf("/from");
        int toIndex = Arrays.asList(tokens).indexOf("/to");

        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("  '/from' must be before '/to'.\n" + HELP_SUFFIX);
        }

        if (fromIndex == 0) {
            throw new IllegalArgumentException("  Please provide a task name.\n" + HELP_SUFFIX);
        }

        if (fromIndex == toIndex - 1) {
            throw new IllegalArgumentException("  Please provide a start time.\n" + HELP_SUFFIX);
        }

        if (toIndex == tokens.length - 1) {
            throw new IllegalArgumentException("  Please provide an end time.\n" + HELP_SUFFIX);
        }

        String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, fromIndex));
        String start = String.join(" ", Arrays.copyOfRange(tokens, fromIndex + 1, toIndex));
        String end = String.join(" ", Arrays.copyOfRange(tokens, toIndex + 1, tokens.length));
        opTask = Optional.of(new Event(taskName, start, end));
    }

    public void run(TaskList taskList, Ui ui) {
        if (opTask.isPresent()) {
            taskList.addTask(opTask.get(), ui);
        } else {
            ui.printEventHelp();
        }
    }
}
