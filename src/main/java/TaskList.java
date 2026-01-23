import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public void addTask(Task task, Ui ui) {
        taskList.add(task);
        ui.printSuccess(String.format("  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                taskList.size()));
    }

    public void deleteTask(int index, Ui ui) {
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.printSuccess(String.format("  Noted. I've removed this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                taskList.size()));
    }

    public void markTask(int index, Ui ui) {
        taskList.get(index).markAsDone();
        ui.printSuccess(String.format("  Nice! I've marked this task as done:\n"
                + "    %s\n\n> ",
                taskList.get(index).toString()));
    }

    public void unmarkTask(int index, Ui ui) {
        taskList.get(index).markAsNotDone();
        ui.printSuccess(String.format("  OK, I've marked this task as not done yet:\n"
                + "    %s\n\n> ",
                taskList.get(index).toString()));
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
}
