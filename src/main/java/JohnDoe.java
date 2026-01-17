import java.util.Scanner;

public class JohnDoe {
    private static Task[] list = new Task[100];
    private static int listIndex = -1;

    public static void main(String[] args) {
        System.out.printf("Hello! I'm JohnDoe.\n"
                + "What can I do for you?\n"
                + "Enter 'help' for a list of available commands.\n\n> ");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().stripLeading().stripTrailing();
            String[] commandDetails = userInput.split(" ", 2);

            System.out.printf("\n");

            if (commandDetails[0].equals("bye")) {
                scanner.close();
                System.out.printf("Bye. Hope to see you again soon!\n");
                break;
            } else if (commandDetails[0].equals("help")) {
                System.out.printf("  Available commands:\n"
                        + "    list\n"
                        + "    todo\n"
                        + "    deadline\n"
                        + "    event\n"
                        + "    mark\n"
                        + "    unmark\n"
                        + "    bye\n"
                        + "  For more details, enter a command with no arguments.\n"
                        + "  Commands are case-sensitive.\n");
            } else if (commandDetails[0].equals("list")) {
                listTasks();
            } else if (commandDetails[0].equals("todo")
                    || commandDetails[0].equals("deadline")
                    || commandDetails[0].equals("event")) {
                if (commandDetails.length > 1) {
                    addTask(commandDetails[0], commandDetails[1]);
                } else {
                    printAddHelp(commandDetails[0]);
                }
            } else if (commandDetails[0].equals("mark")
                    || commandDetails[0].equals("unmark")) {
                if (commandDetails.length > 1) {
                    toggleTask(commandDetails[0], commandDetails[1]);
                } else {
                    printToggleHelp(commandDetails[0]);
                }
            } else {
                System.out.printf("Enter 'help' for a list of all available commands.\n");
            }

            System.out.printf("\n> ");
        }
    }

    private static void addTask(String taskType, String taskDetails) {
        listIndex++;

        switch (taskType) {
        case "event": {
            String[] tokens = taskDetails.split("/from");
            String[] timings = tokens[1].split("/to");
            String taskName = tokens[0].stripLeading().stripTrailing();
            String start = timings[0].stripLeading().stripTrailing();
            String end = timings[1].stripLeading().stripTrailing();
            list[listIndex] = new Event(taskName, start, end);
            break;
        }
        case "deadline": {
            String[] tokens = taskDetails.split("/by");
            String taskName = tokens[0].stripLeading().stripTrailing();
            String deadline = tokens[1].stripLeading().stripTrailing();
            list[listIndex] = new Deadline(taskName, deadline);
            break;
        }
        default: {
            String taskName = taskDetails.stripLeading().stripTrailing();
            list[listIndex] = new Todo(taskName);
            break;
        }
        }

        System.out.printf("  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n",
                list[listIndex].toString(),
                listIndex + 1);
    }

    private static void listTasks() {
        System.out.printf("  Here are the tasks in your list:\n");
        for (int i = 0; i <= listIndex; i++) {
            System.out.printf("    %d. %s\n",
                    i + 1,
                    list[i].toString());
        }
    }

    private static void toggleTask(String command, String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber.stripLeading()) - 1;
            if (command.equals("mark")) {
                list[index].markAsDone();
                System.out.printf("  Nice! I've marked this task as done:\n"
                        + "    %s\n",
                        list[index].toString());
            } else {
                list[index].markAsNotDone();
                System.out.printf("  OK, I've marked this task as not done yet:\n"
                        + "    %s\n",
                        list[index].toString());
            }
        } catch (NumberFormatException e) {
            System.out.printf("  Input is not a number.\n\n");
            printToggleHelp(command);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("  Task does not exist.\n"
                    + "  Use 'list' to view all tasks and their corresponding number.\n");
        } catch (NullPointerException e) {
            System.out.printf("  Task does not exist.\n"
                    + "  Use 'list' to view all tasks and their corresponding number.\n");
        }
    }

    private static void printAddHelp(String command) {

    }

    private static void printToggleHelp(String command) {
        System.out.printf("  info    : set a task as %sdone, indicated by '[%s]'\n"
                + "  usage   : %s TASK_NUMBER\n"
                + "  example : %s 1\n"
                + "  Use 'list' to view all tasks and their corresponding number.\n",
                command.equals("mark") ? "" : "not ",
                command.equals("mark") ? "X" : " ",
                command,
                command);
    }
}
