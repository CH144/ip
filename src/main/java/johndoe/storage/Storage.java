package johndoe.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import johndoe.exception.JohnDoeException;
import johndoe.parser.Parser;
import johndoe.task.Task;
import johndoe.tasklist.TaskList;
import johndoe.ui.Ui;

/**
 * Handles the loading and saving of recorded {@code Task} objects.
 */
public class Storage {
    private static final String LOAD_SUCCESS =
            "  Successfully loaded existing tasks.\n\n";
    private static final String LOAD_ERROR =
            "  Failed to load tasks. Please close and relaunch the app.\n\n";
    private static final String SAVE_SUCCESS =
            "  Successfully saved existing tasks.\n\n";
    private static final String SAVE_ERROR =
            "  Failed to save tasks. Please copy the tasklist manually.\n\n";
    private final Path filePath;

    /**
     * Creates a new {@code Storage} with the filepath for loading and saving.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Reads the stored task entries from a file.
     * Does nothing if file does not exist.
     *
     * @return Success or error message with help message.
     */
    public String read(TaskList taskList) {
        // Code written with help from ChatGPT.
        try {
            if (Files.notExists(filePath)) {
                return Ui.getUnknownCommandHelp();
            }

            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String entry;
                while ((entry = reader.readLine()) != null) {
                    Task t = Parser.entryToTask(entry);
                    taskList.addTaskSilently(t);
                }
            }
            return LOAD_SUCCESS + Ui.getUnknownCommandHelp();
        } catch (IOException e) {
            return LOAD_ERROR;
        } catch (JohnDoeException e) {
            return LOAD_ERROR + e.getMessage();
        }
    }

    /**
     * Writes the recorded tasks to a file in the file entry representation.
     * Tries to create the file if it does not exist.
     * Returns the tasks for manual copying if file write fails.
     *
     * @return Success or error message.
     */
    public String write(TaskList taskList) {
        // Code written with help from ChatGPT.
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(
                    filePath,
                    taskList.toFileEntries(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            String errorMessage = SAVE_ERROR;
            for (String entry : taskList.toFileEntries()) {
                errorMessage += entry;
                errorMessage += "\n";
            }
            return errorMessage;
        }
        return SAVE_SUCCESS;
    }
}
