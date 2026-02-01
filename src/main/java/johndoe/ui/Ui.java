package johndoe.ui;

/**
 * Contains various help messages for the available commands.
 */
public class Ui {
    private Ui() {
        // intentionally blank
    }

    /**
     * Returns help for unknown commands.
     */
    public static String getUnknownCommandHelp() {
        return "  Enter 'help' for a list of all available commands.\n\n";
    }

    /**
     * Returns all available commands.
     */
    public static String getHelp() {
        return "  Available commands:\n"
                + "    list\n"
                + "    find\n"
                + "    todo\n"
                + "    deadline\n"
                + "    event\n"
                + "    delete\n"
                + "    mark\n"
                + "    unmark\n"
                + "    bye\n\n"
                + "  For more details, enter a command with no arguments.\n"
                + "  Commands are case-sensitive.\n\n";
    }

    /**
     * Returns help for the 'find' command.
     */
    public static String getFindHelp() {
        return "  info: lists tasks that contain keywords.\n"
                + "  usage: find KEY_WORDS\n"
                + "  example: find project meeting\n\n";
    }

    /**
     * Returns help for the 'todo' command.
     */
    public static String getTodoHelp() {
        return "  info: add a task.\n"
                + "  usage: todo TASK_NAME\n"
                + "  example: todo watch lecture recording\n\n";
    }

    /**
     * Returns help for the 'deadline' command.
     */
    public static String getDeadlineHelp() {
        return "  info: add a task with a deadline.\n"
                + "  usage: deadline TASK_NAME /by dd/MM/yyyy HHmm\n"
                + "  example: deadline week 3 iP /by 30/01/2026 1600\n\n";
    }

    /**
     * Returns help for the 'event' command.
     */
    public static String getEventHelp() {
        return "  info: add a task with a start and end.\n"
                + "  usage: event TASK_NAME /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\n"
                + "  example: event tP meeting /from 30/01/2026 1800 /to 30/01/2026 1900\n\n";
    }

    /**
     * Returns help for the 'delete' command.
     */
    public static String getDeleteHelp() {
        return "  info: removes an existing task.\n"
                + "  usage: delete TASK_NUMBER\n"
                + "  example: delete 1\n\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n";
    }

    /**
     * Returns help for the 'mark' command.
     */
    public static String getMarkHelp() {
        return "  info: set a task as done, indicated by '[X]'.\n"
                + "  usage: mark TASK_NUMBER\n"
                + "  example: mark 1\n\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n";
    }

    /**
     * Returns help for the 'unmark' command.
     */
    public static String getUnmarkHelp() {
        return "  info: set a task as not done, indicated by '[ ]'.\n"
                + "  usage: unmark TASK_NUMBER\n"
                + "  example: unmark 1\n\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n";
    }
}
