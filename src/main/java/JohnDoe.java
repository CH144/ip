public class JohnDoe {
    private TaskList tasklist;
    private Ui ui;

    private JohnDoe() {
        tasklist = new TaskList();
        ui = new Ui();
    }

    private void run() {
        ui.printGreeting();

        boolean isBye = false;
        while (!isBye) {
            try {
                String userInput = ui.readUserInput();
                Command command = Parser.parse(userInput);
                command.run(tasklist, ui);
                isBye = command.isBye();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.printf("  Task number does not exist.\n"
                        + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ");
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new JohnDoe().run();
    }
}
