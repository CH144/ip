package johndoe.parser;

import static johndoe.task.Task.SAVE_TIME_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import johndoe.command.ByeCommand;
import johndoe.command.Command;
import johndoe.command.DeadlineCommand;
import johndoe.command.DeleteCommand;
import johndoe.command.EventCommand;
import johndoe.command.FindCommand;
import johndoe.command.HelpCommand;
import johndoe.command.ListCommand;
import johndoe.command.MarkCommand;
import johndoe.command.TodoCommand;
import johndoe.command.UnknownCommand;
import johndoe.command.UnmarkCommand;
import johndoe.exception.JohnDoeException;
import johndoe.task.Deadline;
import johndoe.task.Event;
import johndoe.task.Task;
import johndoe.task.Todo;

/**
 * Interprets an input into the required object.
 * Currently supports {@code String} to {@code Command} for user input,
 * and {@code String} to {@code Task} for file entries.
 */
public class Parser {
    private Parser() {
        // intentionally blank
    }

    /**
     * Parses a user input {@code String} into a {@code Command} object.
     *
     * @throws JohnDoeException If {@code Command} instantiation has an error.
     */
    public static Command inputToCommand(String userInput) throws JohnDoeException {
        String[] tokens = userInput.split("\\s+", 2);
        String command = tokens[0];

        if (tokens.length > 1) {
            return parseWithArg(command, tokens[1]);
        } else {
            return parseWithNoArg(command);
        }
    }

    private static Command parseWithNoArg(String command) throws JohnDoeException {
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand();
        case "todo":
            return new TodoCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "delete":
            return new DeleteCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        default:
            return new UnknownCommand();
        }
    }

    private static Command parseWithArg(String command, String arg) throws JohnDoeException {
        switch (command) {
        case "find":
            return new FindCommand(arg);
        case "todo":
            return new TodoCommand(arg);
        case "deadline":
            return new DeadlineCommand(arg);
        case "event":
            return new EventCommand(arg);
        case "delete":
            return new DeleteCommand(arg);
        case "mark":
            return new MarkCommand(arg);
        case "unmark":
            return new UnmarkCommand(arg);
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Parses a file entry {@code String} to a {@code Task} object.
     *
     * @throws JohnDoeException If the file entry format is incorrect.
     */
    public static Task entryToTask(String entry) throws JohnDoeException {
        String errorMessage = "  Invalid entry: " + entry + "\n";

        String[] tokens = entry.split("\\|");

        if (tokens.length < 3) {
            throw new JohnDoeException(errorMessage);
        }

        switch (tokens[0].strip()) {
        case "T":
            return entryToTodo(entry, tokens);
        case "D":
            return entryToDeadline(entry, tokens);
        case "E":
            return entryToEvent(entry, tokens);
        default:
            throw new JohnDoeException(errorMessage);
        }
    }

    private static Todo entryToTodo(String entry, String[] tokens) throws JohnDoeException {
        String errorMessage = "  Invalid entry: "
                + entry
                + "\n  Todo should be of the format: 'T | IS_TASK_DONE | TASK_NAME'\n"
                + "  Where IS_TASK_DONE may be 1 for done or 0 for not done.\n";

        if (tokens.length != 3) {
            throw new JohnDoeException(errorMessage);
        }

        String taskName = tokens[2].strip();

        if (taskName.isBlank()) {
            throw new JohnDoeException(errorMessage);
        }

        Todo todo = new Todo(taskName);
        switch (tokens[1].strip()) {
        case "1":
            todo.markAsDone();
            return todo;
        case "0":
            return todo;
        default:
            throw new JohnDoeException(errorMessage);
        }
    }

    private static Deadline entryToDeadline(String entry, String[] tokens) throws JohnDoeException {
        String errorMessage = "  Invalid entry: "
                + entry
                + "\n  Deadline should be of the format: 'D | IS_TASK_DONE | TASK_NAME | DEADLINE'\n"
                + "  Where IS_TASK_DONE may be 1 for done or 0 for not done,\n"
                + "  and DEADLINE is of the format 'yyyy-MM-dd HHmm', such as: '2026-01-01 1630'\n";

        if (tokens.length != 4) {
            throw new JohnDoeException(errorMessage);
        }

        String taskName = tokens[2].strip();

        if (taskName.isBlank()) {
            throw new JohnDoeException(errorMessage);
        }

        try {
            Deadline deadline = new Deadline(
                    taskName,
                    LocalDateTime.parse(tokens[3].strip(), SAVE_TIME_FORMAT));
            switch (tokens[1].strip()) {
            case "1":
                deadline.markAsDone();
                return deadline;
            case "0":
                return deadline;
            default:
                throw new JohnDoeException(errorMessage);
            }
        } catch (DateTimeParseException e) {
            throw new JohnDoeException(errorMessage);
        }
    }

    private static Event entryToEvent(String entry, String[] tokens) throws JohnDoeException {
        String errorMessage = "  Invalid entry: "
                + entry
                + "\n  Event should be of the format: 'E | IS_TASK_DONE | TASK_NAME | START ~ END'\n"
                + "  Where IS_TASK_DONE may be 1 for done or 0 for not done,\n"
                + "  and START and END are of the format 'yyyy-MM-dd HHmm', such as: '2026-01-01 1630'\n";

        if (tokens.length != 4) {
            throw new JohnDoeException(errorMessage);
        }

        String taskName = tokens[2].strip();
        String[] timings = tokens[3].split("\\~", 2);

        if (taskName.isBlank() || timings.length != 2) {
            throw new JohnDoeException(errorMessage);
        }

        try {
            Event event = new Event(
                    taskName,
                    LocalDateTime.parse(timings[0].strip(), SAVE_TIME_FORMAT),
                    LocalDateTime.parse(timings[1].strip(), SAVE_TIME_FORMAT));
            switch (tokens[1].strip()) {
            case "1":
                event.markAsDone();
                return event;
            case "0":
                return event;
            default:
                throw new JohnDoeException(errorMessage);
            }
        } catch (DateTimeParseException e) {
            throw new JohnDoeException(errorMessage);
        }
    }
}
