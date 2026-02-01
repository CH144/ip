package johndoe.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import johndoe.exception.JohnDoeException;
import johndoe.storage.Storage;
import johndoe.task.Deadline;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Represents the {@code Command} to add a {@code Deadline}.
 */
public class DeadlineCommand extends Command {
    private static final String HELP_SUFFIX = "  Enter 'deadline' for more help.\n\n";
    private Optional<Deadline> opTask;

    /**
     * Creates a new {@code DeadlineCommand} that will print help.
     */
    public DeadlineCommand() {
        super(false);
        opTask = Optional.empty();
    }

    /**
     * Creates a new {@code DeadlineCommand} that will add a {@code Deadline}.
     *
     * @throws JohnDoeException If user input is not in the expected format.
     */
    public DeadlineCommand(String input) throws JohnDoeException {
        super(false);

        try {
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            opTask = Optional.of(new Deadline(taskName, LocalDateTime.parse(deadline, formatter)));
        } catch (DateTimeParseException e) {
            throw new JohnDoeException("  Invalid date & time format.\n" + HELP_SUFFIX);
        }
    }

    /**
     * Adds a {@code Deadline}, or prints the help for the command.
     *
     * @return Successful add or help message.
     */
    public String run(TaskList taskList, Ui ui, Storage storage) {
        if (opTask.isPresent()) {
            return taskList.addTask(opTask.get(), ui);
        } else {
            return ui.getDeadlineHelp();
        }
    }
}
