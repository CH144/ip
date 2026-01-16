import java.util.Scanner;

public class JohnDoe {
    private static Task[] list = new Task[100];
    private static int listIndex = -1;

    public static void main(String[] args) {
        System.out.println("Hello! I'm JohnDoe");
        System.out.println("What can I do for you?");
        printBar();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            String[] commandDetails = userInput.split(" ", 2);

            printBar();

            if (commandDetails[0].equalsIgnoreCase("bye")) {
                scanner.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (commandDetails[0].equalsIgnoreCase("list")) {
                listTasks();
            } else if (commandDetails[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(commandDetails[1]) - 1;
                markTask(index);
            } else if (commandDetails[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(commandDetails[1]) - 1;
                unmarkTask(index);
            } else if (commandDetails[0].equalsIgnoreCase("todo")) {
                addTask(commandDetails[1]);
            } else if (commandDetails[0].equalsIgnoreCase("deadline")) {
                String[] taskDetails = commandDetails[1].split(" /by ");
                addTask(taskDetails[0], taskDetails[1]);
            } else if (commandDetails[0].equalsIgnoreCase("event")) {
                String[] taskDetails = commandDetails[1].split(" /from ");
                String[] timings = taskDetails[1].split(" /to ");
                addTask(taskDetails[0], timings[0], timings[1]);
            } else {
                System.out.println("  Sorry, I don't understand the command!");
            }

            printBar();
        }
    }

    private static void addTask(String taskName) {
        listIndex++;
        list[listIndex] = new Todo(taskName);
        System.out.printf("  Got it. I've added this task:\n   %s\n",
                list[listIndex].toString());
        System.out.printf("  Now you have %d tasks in the list.\n",
                listIndex + 1);
    }

    private static void addTask(String taskName, String deadline) {
        listIndex++;
        list[listIndex] = new Deadline(taskName, deadline);
        System.out.printf("  Got it. I've added this task:\n   %s\n",
                list[listIndex].toString());
        System.out.printf("  Now you have %d tasks in the list.\n",
                listIndex + 1);
    }

    private static void addTask(String taskName, String start, String end) {
        listIndex++;
        list[listIndex] = new Event(taskName, start, end);
        System.out.printf("  Got it. I've added this task:\n   %s\n",
                list[listIndex].toString());
        System.out.printf("  Now you have %d tasks in the list.\n",
                listIndex + 1);
    }

    private static void listTasks() {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i <= listIndex; i++) {
            System.out.printf("  %d. %s\n",
                    i + 1,
                    list[i].toString());
        }
    }

    private static void markTask(int index) {
        if (index < 0 || index > listIndex) {
            return;
        }
        list[index].markAsDone();
        System.out.printf("  Nice! I've marked this task as done:\n   %s\n",
                list[index].toString());
    }

    private static void unmarkTask(int index) {
        if (index < 0 || index > listIndex) {
            return;
        }
        list[index].markAsNotDone();
        System.out.printf("  OK, I've marked this task as not done yet:\n   %s\n",
                list[index].toString());
    }

    private static void printBar() {
        System.out.println(" --------------------------------------------------");
    }
}
