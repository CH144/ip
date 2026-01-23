public class JohnDoe {
    private TaskList taskList;
    private Ui ui;

    private JohnDoe() {
        taskList = new TaskList();
        ui = new Ui();
    }

    private void run() {
        ui.printGreeting();

        boolean isBye = false;
        while (!isBye) {
            try {
                String userInput = ui.readUserInput();
                Command command = Parser.parse(userInput);
                command.run(taskList, ui);
                isBye = command.isBye();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.printError("  Task number does not exist.\n"
                        + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ");
            } catch (IllegalArgumentException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new JohnDoe().run();
    }
}
