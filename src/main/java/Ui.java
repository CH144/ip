class Ui {
    private Ui() {
        // intentionally blank
    }

    public static void printGreeting() {
        System.out.printf("  Hello! I'm JohnDoe.\n"
                + "  What can I do for you?\n"
                + "  Enter 'help' for a list of available commands.\n\n> ");
    }

    public static void printFarewell() {
        System.out.printf("  Bye. Hope to see you again soon!\n");
    }

    public static void printUnknownCommandHelp() {
        System.out.printf("  Enter 'help' for a list of all available commands.\n\n> ");
    }

    public static void printCommandsListHelp() {
        System.out.printf("  Available commands:\n"
                + "    list\n"
                + "    todo\n"
                + "    deadline\n"
                + "    event\n"
                + "    delete\n"
                + "    mark\n"
                + "    unmark\n"
                + "    bye\n"
                + "  For more details, enter a command with no arguments.\n"
                + "  Commands are case-sensitive.\n\n> ");
    }

    public static void printTodoHelp() {
        System.out.printf("  info    : add a task.\n"
                + "  usage   : todo TASK_NAME\n"
                + "  example : todo watch lecture recording\n\n> ");
    }

    public static void printDeadlineHelp() {
        System.out.printf("  info    : add a task with a deadline.\n"
                + "  usage   : deadline TASK_NAME /by DEADLINE\n"
                + "  example : deadline week 3 iP /by 30 Jan\n\n> ");
    }

    public static void printEventHelp() {
        System.out.printf("  info    : add a task with a start and end.\n"
                + "  usage   : event TASK_NAME /from START_TIME /to END_TIME\n"
                + "  example : event tP meeting /from Mon 10am /to 12nn\n\n> ");
    }

    public static void printDeleteHelp() {
        System.out.printf("  info    : removes an existing task.\n"
                + "  usage   : delete TASK_NUMBER\n"
                + "  example : delete 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }

    public static void printMarkHelp() {
        System.out.printf("  info    : set a task as done, indicated by '[X]'.\n"
                + "  usage   : mark TASK_NUMBER\n"
                + "  example : mark 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }

    public static void printUnmarkHelp() {
        System.out.printf("  info    : set a task as not done, indicated by '[ ]'.\n"
                + "  usage   : unmark TASK_NUMBER\n"
                + "  example : unmark 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }
}
