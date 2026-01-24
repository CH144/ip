package johndoe.tasklist;

import johndoe.task.Task;
import johndoe.ui.JohnDoeException;
import johndoe.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList manages operations to the recorded tasks.
 */
public class TaskList {
    private static final String OOB_ERROR = "  Task number does not exist.\n"
            + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ";
    private ArrayList<Task> taskList;

    /**
     * Constructor of the TaskList class.
     * Creates a new ArrayList of Task to be updated.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task.
     * Used when reading data from a file.
     * Has no output.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Adds a task.
     * Used when user commands.
     * Has output on success.
     */
    public void addTask(Task task, Ui ui) {
        taskList.add(task);
        ui.printSuccess(String.format("  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                taskList.size()));
    }

    /**
     * Deletes a task.
     * Has output on success.
     * Throws JohnDoeException if the index is out of bounds.
     */
    public void deleteTask(int index, Ui ui) throws JohnDoeException {
        try {
            Task task = taskList.get(index);
            taskList.remove(index);
            ui.printSuccess(String.format("  Noted. I've removed this task:\n"
                    + "    %s\n"
                    + "  Now you have %d tasks in the list.\n\n> ",
                    task.toString(),
                    taskList.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new JohnDoeException(OOB_ERROR);
        }
    }

    /**
     * Marks a task as done.
     * Has output on success.
     * Throws JohnDoeException if the index is out of bounds.
     */
    public void markTask(int index, Ui ui) throws JohnDoeException {
        try {
            taskList.get(index).markAsDone();
            ui.printSuccess(String.format("  Nice! I've marked this task as done:\n"
                    + "    %s\n\n> ",
                    taskList.get(index).toString()));
        } catch (IndexOutOfBoundsException e) {
            throw new JohnDoeException(OOB_ERROR);
        }
    }

    /**
     * Marks a task as not done.
     * Has output on success.
     * Throws JohnDoeException if the index is out of bounds.
     */
    public void unmarkTask(int index, Ui ui) throws JohnDoeException {
        try {
            taskList.get(index).markAsNotDone();
            ui.printSuccess(String.format("  OK, I've marked this task as not done yet:\n"
                    + "    %s\n\n> ",
                    taskList.get(index).toString()));
        } catch (IndexOutOfBoundsException e) {
            throw new JohnDoeException(OOB_ERROR);
        }
    }

    /**
     * Prints all recorded tasks in the order of record.
     */
    public void printTasks(Ui ui) {
        String output = "  Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("    %d. %s\n",
                    i + 1,
                    taskList.get(i).toString());
        }
        output += "\n> ";
        ui.printSuccess(output);
    }

    /**
     * Prints all recorded tasks that contain the keywords.
     */
    public void findTasks(String keyWords, Ui ui) {
        String output = String.format("  Here are the tasks that contain '%s':\n", keyWords);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).taskNameContains(keyWords)) {
                output += String.format("    %d. %s\n",
                        i + 1,
                        taskList.get(i).toString());
            }
        }
        output += "\n> ";
        ui.printSuccess(output);
    }

    /**
     * Returns the tasks in the format for saving to a file.
     */ 
    public List<String> toFileEntries() {
        return taskList.stream().map(t -> t.toFileEntry()).toList();
    }
}
