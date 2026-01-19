import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasklist;

    public TaskList() {
        tasklist = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasklist.add(task);
        System.out.printf("  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                tasklist.size());
    }

    public void deleteTask(int index) {
        Task task = tasklist.get(index);
        tasklist.remove(index);
        System.out.printf("  Noted. I've removed this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                tasklist.size());
    }

    public void markTask(int index) {
        tasklist.get(index).markAsDone();
        System.out.printf("  Nice! I've marked this task as done:\n"
                + "    %s\n\n> ",
                tasklist.get(index).toString());
    }

    public void unmarkTask(int index) {
        tasklist.get(index).markAsNotDone();
        System.out.printf("  OK, I've marked this task as not done yet:\n"
                + "    %s\n\n> ",
                tasklist.get(index).toString());
    }

    public void printTasks() {
        System.out.printf("  Here are the tasks in your list:\n");
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.printf("    %d. %s\n",
                    i + 1,
                    tasklist.get(i).toString());
        }
        System.out.printf("\n> ");
    }
}
