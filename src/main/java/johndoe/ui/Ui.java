package johndoe.ui;

import java.util.Scanner;

/**
 * Ui handles all of the input and output.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor of the Ui class.
     * Creates a new Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads one line of user input.
     */
    public String readUserInput() {
        return scanner.nextLine().strip();
    }

    /**
     * Prints a greeting when the app first starts.
     */
    public void printGreeting() {
        System.out.printf("  Hello! I'm JohnDoe.\n"
                + "  What can I do for you?\n"
                + "  Enter 'help' for a list of available commands.\n\n> ");
    }

    /**
     * Prints a farewell upon the 'bye' command.
     */
    public void printFarewell() {
        scanner.close();
        System.out.printf("  Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints a message that indicates an operation succeeded.
     */
    public void printSuccess(String message) {
        System.out.printf(message);
    }

    /**
     * Prints a message that indicates an operation failed.
     */
    public void printError(String message) {
        System.out.printf(message);
    }

    /**
     * Prints a help message for unknown commands.
     */
    public void printUnknownCommandHelp() {
        System.out.printf("  Enter 'help' for a list of all available commands.\n\n> ");
    }

    /**
     * Prints all available commands.
     */
    public void printHelp() {
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

    /**
     * Prints help for the 'todo' command.
     */
    public void printTodoHelp() {
        System.out.printf("  info    : add a task.\n"
                + "  usage   : todo TASK_NAME\n"
                + "  example : todo watch lecture recording\n\n> ");
    }

    /**
     * Prints help for the 'deadline' command.
     */
    public void printDeadlineHelp() {
        System.out.printf("  info    : add a task with a deadline.\n"
                + "  usage   : deadline TASK_NAME /by dd/MM/yyyy HHmm\n"
                + "  example : deadline week 3 iP /by 30/01/2026 1600\n\n> ");
    }

    /**
     * Prints help for the 'event' command.
     */
    public void printEventHelp() {
        System.out.printf("  info    : add a task with a start and end.\n"
                + "  usage   : event TASK_NAME /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\n"
                + "  example : event tP meeting /from 30/01/2026 1800 /to 30/01/2026 1900\n\n> ");
    }

    /**
     * Prints help for the 'delete' command.
     */
    public void printDeleteHelp() {
        System.out.printf("  info    : removes an existing task.\n"
                + "  usage   : delete TASK_NUMBER\n"
                + "  example : delete 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }

    /**
     * Prints help for the 'mark' command.
     */
    public void printMarkHelp() {
        System.out.printf("  info    : set a task as done, indicated by '[X]'.\n"
                + "  usage   : mark TASK_NUMBER\n"
                + "  example : mark 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }

    /**
     * Prints help for the 'unmark' command.
     */
    public void printUnmarkHelp() {
        System.out.printf("  info    : set a task as not done, indicated by '[ ]'.\n"
                + "  usage   : unmark TASK_NUMBER\n"
                + "  example : unmark 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }
}
