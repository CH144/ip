import java.util.Scanner;

public class JohnDoe {
    private static Task[] list = new Task[100];
    private static int listIndex = -1;

    public static void main(String[] args) {
        System.out.println("Hello! I'm JohnDoe");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            String[] tokens = userInput.split(" ", 2);

            printBar();

            if (tokens[0].equalsIgnoreCase("bye")) {
                scanner.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (tokens[0].equalsIgnoreCase("list")) {
                listTasks();
            } else if (tokens[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(tokens[1]) - 1;
                markTask(index);
            } else if (tokens[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(tokens[1]) - 1;
                unmarkTask(index);
            } else if (tokens[0].equalsIgnoreCase("todo")) {
                addTodo(userInput);
            } else {
                System.out.println("  Sorry, I don't understand the command!");
            }

            printBar();
        }
    }

    private static void addTodo(String taskName) {
        listIndex++;
        list[listIndex] = new Todo(taskName);
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
