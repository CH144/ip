class Parser {
    private Parser() {
        // intentionally blank
    }

    public static Command parse(String userInput) {
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
}
