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

            if (userInput.equalsIgnoreCase("bye")) {
                scanner.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
        }
    }

    private static void addTask(String taskName) {
        listIndex++;
        list[listIndex] = new Task(taskName);

        System.out.printf("added: %s\n", taskName);
    }

    private static void listTasks() {
        for (int i = 0; i <= listIndex; i++) {
            System.out.printf("%d. %s\n",
                    i + 1,
                    list[i].toString());
        }
    }
}
