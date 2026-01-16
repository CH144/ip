import java.util.Scanner;

public class JohnDoe {
    public static void main(String[] args) {
        System.out.println("Hello! I'm JohnDoe");
        System.out.println("What can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userRequest = scanner.nextLine();

            if (userRequest.equalsIgnoreCase("bye")) {
                scanner.close();
                break;
            }

            System.out.println(userRequest);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
