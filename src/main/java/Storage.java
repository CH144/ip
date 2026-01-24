import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Storage {
    private static final String LOAD_ERROR =
            "  Failed to load tasks. Please try again.\n";
    private static final String SAVE_ERROR =
            "  Failed to save tasks. Please copy the tasklist manually.\n";
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // by ChatGPT
    public void read(TaskList taskList, Ui ui) {
        try {
            if (Files.exists(filePath)) {
                try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                    String entry;
                    while ((entry = reader.readLine()) != null) {
                        Task t = Parser.entryToTask(entry);
                        taskList.addTask(t);
                    }
                }
            }
        } catch (IOException e) {
            ui.printError(LOAD_ERROR);
            System.exit(1);
        } catch (JohnDoeException e) {
            ui.printError(LOAD_ERROR + e.getMessage());
            System.exit(1);
        }
    }

    // by ChatGPT
    public void write(TaskList taskList, Ui ui) {
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(
                    filePath,
                    taskList.toFileEntries(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            ui.printError(SAVE_ERROR);
            for (String entry : taskList.toFileEntries()) {
                ui.printSuccess(entry + "\n");
            }
        }
    }
}
