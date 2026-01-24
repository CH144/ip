package johndoe.tasklist;

import johndoe.exception.JohnDoeException;
import johndoe.task.Task;
import johndoe.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages created {@code Task} objects.
 * Has add, delete, list, find, mark as done, mark as not done methods.
 */
public class TaskList {
    private static final String OOB_ERROR = "  Task number does not exist.\n"
            + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ";
    private ArrayList<Task> taskList;

    /**
     * Creates an empty {@code ArrayList} of {@code Task}.
     * Every new run of {@code JohnDoe} starts with a fresh {@code TaskList}.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task.
     * Used when reading task entries from a file.
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
     * Deletes the task specified by the index.
     * Has output on success.
     *
     * @throws JohnDoeException If the index is out of bounds.
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
     * Marks the task specified by the index as done.
     * Has output on success.
     *
     * @throws JohnDoeException If the index is out of bounds.
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
     * Marks the task specified by the index as not done.
     * Has output on success.
     *
     * @throws JohnDoeException If the index is out of bounds.
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
     * Prints all recorded tasks in the order they were added.
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
     * Prints all recorded tasks with task name that contains the keywords as substring.
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
