import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class TaskList {
    private ArrayList<Task> tasklist;

    public TaskList() {
        tasklist = new ArrayList<Task>();
    }

    public void addTodo(String input) {
        String[] tokens = input.split("\\s+");
        String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, tokens.length));
        Todo t = new Todo(taskName);
        tasklist.add(t);
        printSuccessfulAdd(t);
    }

    public void addDeadline(String input) {
        try {
            String[] tokens = input.split("\\s+");

            for (String token : tokens) {
                if (token != null && !token.equals("/by") && token.contains("/by")) {
                    throw new IllegalArgumentException(
                            "  There should be spaces before and after '/by'.\n");
                }
            }

            if (Collections.frequency(Arrays.asList(tokens), "/by") != 1) {
                throw new IllegalArgumentException("  Exactly one '/by' must be used.\n");
            }

            int byIndex = Arrays.asList(tokens).indexOf("/by");

            if (byIndex == 0) {
                throw new IllegalArgumentException("  Please provide a task name.\n");
            }

            if (byIndex == tokens.length - 1) {
                throw new IllegalArgumentException("  Please provide a deadline.\n");
            }

            String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, byIndex));
            String deadline = String.join(" ", Arrays.copyOfRange(tokens, byIndex + 1, tokens.length));
            Deadline d = new Deadline(taskName, deadline);
            tasklist.add(d);
            printSuccessfulAdd(d);
        } catch (IllegalArgumentException e) {
            System.out.printf(e.getMessage() + "  Enter 'deadline' for more help.\n\n> ");
        }
    }

    public void addEvent(String input) {
        try {
            String[] tokens = input.split("\\s+");

            for (String token : tokens) {
                if (token != null && !token.equals("/from") && token.contains("/from")) {
                    throw new IllegalArgumentException(
                            "  There should be spaces before and after '/from'.\n");
                }
            }

            for (String token : tokens) {
                if (token != null && !token.equals("/to") && token.contains("/to")) {
                    throw new IllegalArgumentException(
                            "  There should be spaces before and after '/to'.\n");
                }
            }

            if (Collections.frequency(Arrays.asList(tokens), "/from") != 1) {
                throw new IllegalArgumentException("  Exactly one '/from' must be used.\n");
            }

            if (Collections.frequency(Arrays.asList(tokens), "/to") != 1) {
                throw new IllegalArgumentException("  Exactly one '/to' must be used.\n");
            }

            int fromIndex = Arrays.asList(tokens).indexOf("/from");
            int toIndex = Arrays.asList(tokens).indexOf("/to");

            if (fromIndex > toIndex) {
                throw new IllegalArgumentException("  '/from' must be before '/to'.\n");
            }

            if (fromIndex == 0) {
                throw new IllegalArgumentException("  Please provide a task name.\n");
            }

            if (fromIndex == toIndex - 1) {
                throw new IllegalArgumentException("  Please provide a start time.\n");
            }

            if (toIndex == tokens.length - 1) {
                throw new IllegalArgumentException("  Please provide an end time.\n");
            }

            String taskName = String.join(" ", Arrays.copyOfRange(tokens, 0, fromIndex));
            String start = String.join(" ", Arrays.copyOfRange(tokens, fromIndex + 1, toIndex));
            String end = String.join(" ", Arrays.copyOfRange(tokens, toIndex + 1, tokens.length));
            Event e = new Event(taskName, start, end);
            tasklist.add(e);
            printSuccessfulAdd(e);
        } catch (IllegalArgumentException e) {
            System.out.printf(e.getMessage() + "  Enter 'event' for more help.\n\n> ");
        }
    }

    private void printSuccessfulAdd(Task task) {
        System.out.printf("  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n\n> ",
                task.toString(),
                tasklist.size());
    }

    public void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasklist.get(index);
            tasklist.remove(index);
            System.out.printf("  Noted. I've removed this task:\n"
                    + "    %s\n"
                    + "  Now you have %d tasks in the list.\n\n> ",
                    task.toString(),
                    tasklist.size());
        } catch (NumberFormatException e) {
            System.out.printf("  Invalid format.\n"
                    + "  Enter 'delete' for more help.\n\n> ");
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("  Task does not exist.\n"
                    + "  Enter 'list' to view all task numbers.\n\n> ");
        }
    }

    public void markTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            tasklist.get(index).markAsDone();
            System.out.printf("  Nice! I've marked this task as done:\n"
                    + "    %s\n\n> ",
                    tasklist.get(index).toString());
        } catch (NumberFormatException e) {
            System.out.printf("  Invalid format.\n"
                    + "  Enter 'mark' for more help.\n\n> ");
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("  Task does not exist.\n"
                    + "  Enter 'list' to view all task numbers.\n\n> ");
        }
    }

    public void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            tasklist.get(index).markAsNotDone();
            System.out.printf("  OK, I've marked this task as not done yet:\n"
                    + "    %s\n\n> ",
                    tasklist.get(index).toString());
        } catch (NumberFormatException e) {
            System.out.printf("  Invalid format.\n"
                    + "  Enter 'unmark' for more help.\n\n> ");
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("  Task does not exist.\n"
                    + "  Enter 'list' to view all task numbers.\n\n> ");
        }
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
