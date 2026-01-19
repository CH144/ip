import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasklist;

    public TaskList() {
        tasklist = new ArrayList<Task>();
    }

    public void addTask(Task task, Ui ui) {
        tasklist.add(task);
        ui.printSuccess(String.format("  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                tasklist.size()));
    }

    public void deleteTask(int index, Ui ui) {
        Task task = tasklist.get(index);
        tasklist.remove(index);
        ui.printSuccess(String.format("  Noted. I've removed this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                tasklist.size()));
    }

    public void markTask(int index, Ui ui) {
        tasklist.get(index).markAsDone();
        ui.printSuccess(String.format("  Nice! I've marked this task as done:\n"
                + "    %s\n\n> ",
                tasklist.get(index).toString()));
    }

    public void unmarkTask(int index, Ui ui) {
        tasklist.get(index).markAsNotDone();
        ui.printSuccess(String.format("  OK, I've marked this task as not done yet:\n"
                + "    %s\n\n> ",
                tasklist.get(index).toString()));
    }

    public void printTasks(Ui ui) {
        String output = "  Here are the tasks in your list:\n";
        for (int i = 0; i < tasklist.size(); i++) {
            output += String.format("    %d. %s\n",
                    i + 1,
                    tasklist.get(i).toString());
        }
        output += "\n> ";
        ui.printSuccess(output);
    }
}
