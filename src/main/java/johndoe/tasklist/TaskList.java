package johndoe.tasklist;

import johndoe.task.Task;
import johndoe.ui.JohnDoeException;
import johndoe.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String OOB_ERROR = "  Task number does not exist.\n"
            + "  Enter 'list' to view all tasks and their corresponding number.\n\n> ";
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addTask(Task task, Ui ui) {
        taskList.add(task);
        ui.printSuccess(String.format("  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                taskList.size()));
    }

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

    public List<String> toFileEntries() {
        return taskList.stream().map(t -> t.toFileEntry()).toList();
    }
}
