import java.util.Scanner;

public class JohnDoe {
    public static void main(String[] args) {
        Tasklist tasklist = new Tasklist();
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
