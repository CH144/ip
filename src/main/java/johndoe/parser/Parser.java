package johndoe.parser;

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
import johndoe.task.Deadline;
import johndoe.task.Event;
import johndoe.task.Task;
import johndoe.task.Todo;
import johndoe.ui.JohnDoeException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Parser interprets a given input into the required object.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private Parser() {
        // intentionally blank
    }

    /**
     * Parses a user input into a command object.
     */
    public static Command inputToCommand(String userInput) throws JohnDoeException {
        String[] tokens = userInput.split("\\s+", 2);
        String command = tokens[0];

        if (tokens.length > 1) {
            switch (command) {
            case "find":
                return new FindCommand(tokens[1]);
            case "todo":
                return new TodoCommand(tokens[1]);
            case "deadline":
                return new DeadlineCommand(tokens[1]);
            case "event":
                return new EventCommand(tokens[1]);
            case "delete":
                return new DeleteCommand(tokens[1]);
            case "mark":
                return new MarkCommand(tokens[1]);
            case "unmark":
                return new UnmarkCommand(tokens[1]);
            default:
                return new UnknownCommand();
            }
        } else {
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
    }

    /**
     * Parses a file entry to a task object.
     */
    public static Task entryToTask(String entry) throws JohnDoeException {
        String errorMessage = "  Invalid entry: " + entry + "\n";

        String[] tokens = entry.split("\\|");

        if (tokens.length < 3){
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

        if (taskName.equals("")) {
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

        if (taskName.equals("")) {
            throw new JohnDoeException(errorMessage);
        }

        try {
            Deadline deadline = new Deadline(
                    taskName,
                    LocalDateTime.parse(tokens[3].strip(), FORMATTER));
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

        if (taskName.equals("") || timings.length != 2) {
            throw new JohnDoeException(errorMessage);
        }

        try {
            Event event = new Event(
                    taskName,
                    LocalDateTime.parse(timings[0].strip(), FORMATTER),
                    LocalDateTime.parse(timings[1].strip(), FORMATTER));
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
