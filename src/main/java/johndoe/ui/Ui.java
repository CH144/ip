package johndoe.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readUserInput() {
        return scanner.nextLine().strip();
    }

    public void printGreeting() {
        System.out.printf("  Hello! I'm JohnDoe.\n"
                + "  What can I do for you?\n"
                + "  Enter 'help' for a list of available commands.\n\n> ");
    }

    public void printFarewell() {
        scanner.close();
        System.out.printf("  Bye. Hope to see you again soon!\n");
    }

    public void printSuccess(String message) {
        System.out.printf(message);
    }

    public void printError(String message) {
        System.out.printf(message);
    }

    public void printUnknownCommandHelp() {
        System.out.printf("  Enter 'help' for a list of all available commands.\n\n> ");
    }

    public void printHelp() {
        System.out.printf("  Available commands:\n"
                + "    list\n"
                + "    find\n"
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
     * Prints the help for the 'find' command.
     */
    public void printFindHelp() {
        System.out.printf("  info    : lists tasks that contain keywords.\n"
                + "  usage   : find KEY_WORDS\n"
                + "  example : find project meeting\n\n> ");
    }

    public void printTodoHelp() {
        System.out.printf("  info    : add a task.\n"
                + "  usage   : todo TASK_NAME\n"
                + "  example : todo watch lecture recording\n\n> ");
    }

    public void printDeadlineHelp() {
        System.out.printf("  info    : add a task with a deadline.\n"
                + "  usage   : deadline TASK_NAME /by dd/MM/yyyy HHmm\n"
                + "  example : deadline week 3 iP /by 30/01/2026 1600\n\n> ");
    }

    public void printEventHelp() {
        System.out.printf("  info    : add a task with a start and end.\n"
                + "  usage   : event TASK_NAME /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\n"
                + "  example : event tP meeting /from 30/01/2026 1800 /to 30/01/2026 1900\n\n> ");
    }

    public void printDeleteHelp() {
        System.out.printf("  info    : removes an existing task.\n"
                + "  usage   : delete TASK_NUMBER\n"
                + "  example : delete 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }

    public void printMarkHelp() {
        System.out.printf("  info    : set a task as done, indicated by '[X]'.\n"
                + "  usage   : mark TASK_NUMBER\n"
                + "  example : mark 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }

    public void printUnmarkHelp() {
        System.out.printf("  info    : set a task as not done, indicated by '[ ]'.\n"
                + "  usage   : unmark TASK_NUMBER\n"
                + "  example : unmark 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n\n> ");
    }
}
