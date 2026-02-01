package johndoe.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import johndoe.exception.JohnDoeException;
import johndoe.storage.Storage;
import johndoe.task.Event;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to add an {@code Event}.
 */
public class EventCommand extends Command {
    private static final String HELP_SUFFIX = "  Enter 'event' for more help.\n\n";
    private Optional<Event> opTask;

    /**
     * Creates a new {@code EventCommand} that will print help.
     */
    public EventCommand() {
        super(false);
        opTask = Optional.empty();
    }

    /**
     * Creates a new {@code EventCommand} that will add a {@code Event}.
     *
     * @throws JohnDoeException If user input is not in the expected format.
     */
    public EventCommand(String input) throws JohnDoeException {
        super(false);

        try {
            String[] tokens = input.split("\\s+");

            for (String token : tokens) {
                if ((token != null && !token.equals("/from") && token.contains("/from"))
                        || (token != null && !token.equals("/to") && token.contains("/to"))) {
                    throw new JohnDoeException(
                            "  There should be spaces before and after '/from' and '/to'.\n" + HELP_SUFFIX);
                }
            }

            if (Collections.frequency(Arrays.asList(tokens), "/from") != 1
                    || Collections.frequency(Arrays.asList(tokens), "/to") != 1) {
                throw new JohnDoeException(
                            "  Exactly one '/from' and one '/to' must be used.\n" + HELP_SUFFIX);
            }

            int fromIndex = Arrays.asList(tokens).indexOf("/from");
            int toIndex = Arrays.asList(tokens).indexOf("/to");

            if (fromIndex > toIndex) {
                throw new JohnDoeException("  '/from' must be before '/to'.\n" + HELP_SUFFIX);
            }

            if (fromIndex == 0) {
                throw new JohnDoeException("  Please provide a task name.\n" + HELP_SUFFIX);
            }

            if (fromIndex == toIndex - 1) {
                throw new JohnDoeException("  Please provide a start time.\n" + HELP_SUFFIX);
            }

            if (toIndex == tokens.length - 1) {
                throw new JohnDoeException("  Please provide an end time.\n" + HELP_SUFFIX);
            }

            String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, fromIndex));
            String start = String.join(" ", Arrays.copyOfRange(tokens, fromIndex + 1, toIndex));
            String end = String.join(" ", Arrays.copyOfRange(tokens, toIndex + 1, tokens.length));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            opTask = Optional.of(new Event(taskName,
                    LocalDateTime.parse(start, formatter),
                    LocalDateTime.parse(end, formatter)));
        } catch (DateTimeParseException e) {
            throw new JohnDoeException("  Invalid date & time format.\n" + HELP_SUFFIX);
        }
    }

    /**
     * Adds an {@code Event}, or prints the help for the command.
     *
     * @return Successful add or help message.
     */
    public String run(TaskList taskList, Storage storage) {
        if (opTask.isPresent()) {
            return taskList.addTask(opTask.get())
                    + storage.write(taskList);
        } else {
            return Ui.getEventHelp();
        }
    }
}
