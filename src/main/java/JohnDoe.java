import java.util.Scanner;

public class JohnDoe {
    public static void main(String[] args) {
        TaskList tasklist = new TaskList();
        Scanner scanner = new Scanner(System.in);

        Ui.printGreeting();

        while (true) {
            String userInput = scanner.nextLine().strip();
            if (!Parser.parse(userInput, tasklist)) {
                scanner.close();
                break;
            }
        }
    }
}
