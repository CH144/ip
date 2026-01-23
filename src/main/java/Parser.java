import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Parser {
    private Parser() {
        // intentionally blank
    }

    public static Command inputToCommand(String userInput) throws JohnDoeException {
        String[] tokens = userInput.split("\\s+", 2);
        String command = tokens[0];

        if (tokens.length > 1) {
            switch (command) {
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

        if (tokens[2].strip().equals("")) {
            throw new JohnDoeException(errorMessage);
        }

        Todo todo = new Todo(tokens[2].strip());
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            Deadline d = new Deadline(tokens[2].strip(), LocalDateTime.parse(tokens[3].strip(), formatter));
            switch (tokens[1].strip()) {
            case "1":
                d.markAsDone();
                return d;
            case "0":
                return d;
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

        String[] timings = tokens[3].split("\\~", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            Event e = new Event(tokens[2].strip(),
                    LocalDateTime.parse(timings[0].strip(), formatter),
                    LocalDateTime.parse(timings[1].strip(), formatter));

            switch (tokens[1].strip()) {
            case "1":
                e.markAsDone();
                return e;
            case "0":
                return e;
            default:
                throw new JohnDoeException(errorMessage);
            }
        } catch (DateTimeParseException e) {
            throw new JohnDoeException(errorMessage);
        }
    }
}
