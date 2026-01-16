import java.util.Scanner;

public class JohnDoe {
    public static void main(String[] args) {
        System.out.println("Hello! I'm JohnDoe");
        System.out.println("What can I do for you?");

        String[] list = new String[100];
        int listIndex = -1;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                scanner.close();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i <= listIndex; i++) {
                    System.out.printf("%d. %s\n", i + 1, list[i]);
                }
            } else {
                listIndex++;
                list[listIndex] = userInput;

                System.out.printf("added: %s\n", userInput);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
