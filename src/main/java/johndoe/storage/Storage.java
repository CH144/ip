package johndoe.storage;

import johndoe.exception.JohnDoeException;
import johndoe.parser.Parser;
import johndoe.task.Task;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Handles the loading and saving of recorded {@code Task} objects.
 */
public class Storage {
    private static final String LOAD_ERROR =
            "  Failed to load tasks. Please try again.\n";
    private static final String SAVE_ERROR =
            "  Failed to save tasks. Please copy the tasklist manually.\n";
    private Path filePath;

    /**
     * Creates a new {@code Storage} with the filepath for loading and saving.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Reads the stored task entries from a file.
     * Does nothing if file does not exist.
     * Kills the app if the file exists but unable to read correctly.
     */
    public void read(TaskList taskList, Ui ui) {
        // Code written with help from ChatGPT.
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

    /**
     * Writes the recorded tasks to a file in the file entry representation.
     * Tries to create the file if it does not exist.
     * Prints the tasks for manual copying if file write fails.
     */
    public void write(TaskList taskList, Ui ui) {
        // Code written with help from ChatGPT.
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
