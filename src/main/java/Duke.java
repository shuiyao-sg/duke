import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String INDENT_BY_ONE = " ";
    private static final String INDENT_BY_TWO = " " + " ";
    private static final String INDENT_BY_FOUR = " " + " " + " " + " ";
    private static final String HORIZONTAL_LINE = INDENT_BY_FOUR
            + "____________________________________________________________";
    private static final String TASK_DONE = "Nice! I've marked this task as done:";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    Task task = list.get(i);
                    System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE);
                    System.out.println(index + "." + task.getStatusIcon() + " " + task.getDescription());
                }
                System.out.println(HORIZONTAL_LINE);
                System.out.println();
            } else if (input.startsWith("done")) {
                String[] doneCommand = input.split(" ");
                int index = Integer.parseInt(doneCommand[1]) - 1;
                Task task = list.get(index);
                task.markAsDone();
                System.out.println(HORIZONTAL_LINE);
                System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE);
                System.out.println(TASK_DONE);
                System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE + INDENT_BY_TWO);
                System.out.println(task.getStatusIcon() + " " + task.getDescription());
                System.out.println(HORIZONTAL_LINE);
                System.out.println();
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.println(HORIZONTAL_LINE);
                System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE);
                System.out.println("Added:" + " " + input);
                System.out.println(HORIZONTAL_LINE);
                System.out.println();
            }
        }
    }

    private static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE);
        System.out.println("Hello! I'm Duke");
        System.out.print(INDENT_BY_FOUR + INDENT_BY_ONE);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }
}
