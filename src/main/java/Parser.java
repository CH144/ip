class Parser {
    private Parser() {
        // intentionally blank
    }

    public static boolean parse(String userInput, TaskList tasklist) {
        String[] tokens = userInput.split("\\s+", 2);
        String command = tokens[0];

        if (tokens.length > 1) {
            switch (command) {
            case "todo":
                tasklist.addTodo(tokens[1]);
                break;
            case "deadline":
                tasklist.addDeadline(tokens[1]);
                break;
            case "event":
                tasklist.addEvent(tokens[1]);
                break;
            case "mark":
                tasklist.markTask(tokens[1]);
                break;
            case "unmark":
                tasklist.unmarkTask(tokens[1]);
                break;
            default:
                Ui.printUnknownCommandHelp();
                break;
            }
        } else {
            switch (command) {
            case "bye":
                Ui.printFarewell();
                return false;
            case "help":
                Ui.printCommandsListHelp();
                break;
            case "list":
                tasklist.printTasks();
                break;
            case "todo":
                Ui.printTodoHelp();
                break;
            case "deadline":
                Ui.printDeadlineHelp();
                break;
            case "event":
                Ui.printEventHelp();
                break;
            case "mark":
                Ui.printMarkHelp();
                break;
            case "unmark":
                Ui.printUnmarkHelp();
                break;
            default:
                Ui.printUnknownCommandHelp();
                break;
            }
        }
        return true;
    }
}
