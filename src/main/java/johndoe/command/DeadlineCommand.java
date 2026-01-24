package johndoe.command;

import johndoe.exception.JohnDoeException;
import johndoe.task.Deadline;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.util.Arrays;
import java.util.Collections;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * DeadlineCommand can add a Deadline object to the records.
 */
public class DeadlineCommand extends Command {
    private static final String HELP_SUFFIX = "  Enter 'deadline' for more help.\n\n> ";
    private Optional<Deadline> opTask;

    /**
     * Constructor for the DeadlineCommand class for printing help.
     */
    public DeadlineCommand() {
        super(false);
        opTask = Optional.empty();
    }

    /**
     * Constructor for the Deadline class for adding deadline.
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
     * Adds a Deadline object, or prints the help for the command.
     */
    public void run(TaskList taskList, Ui ui) {
        if (opTask.isPresent()) {
            taskList.addTask(opTask.get(), ui);
        } else {
            ui.printDeadlineHelp();
        }
    }
}
