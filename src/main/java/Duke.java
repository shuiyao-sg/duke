import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
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
                bye();
                sc.close();
                break;
            } else if (input.equals("list")) {
                printList(list);
            } else if (input.startsWith("done ")) {
                String[] doneCommand = input.split(" ");
                int index = Integer.parseInt(doneCommand[1]) - 1;
                Task task = list.get(index);
                task.markAsDone();
                printDoneTask(task);
            } else {
                String[] inputArray = input.split(" ");

                if (inputArray[0].equals("todo")) {
                    String des = reformString(inputArray, 1, inputArray.length - 1);
                    Task task = new ToDo(des);
                    list.add(task);
                    printTaskAdded(list, task);
                } else if (inputArray[0].equals("deadline")) {
                    String newInput = reformString(inputArray, 1, inputArray.length - 1);
                    String[] newInputArray = newInput.split("/by");
                    String des = newInputArray[0].trim();
                    String by = newInputArray[1].trim();
                    Task task = new Deadline(des, by);
                    list.add(task);
                    printTaskAdded(list, task);
                } else if (inputArray[0].equals("event")) {
                    String newInput = reformString(inputArray, 1, inputArray.length - 1);
                    String[] newInputArray = newInput.split("/at");
                    String des = newInputArray[0].trim();
                    String at = newInputArray[1].trim();
                    Task task = new Event(des, at);
                    list.add(task);
                    printTaskAdded(list, task);
                }
            }
        }
    }

    private static void greet() {
        System.out.println(HORIZONTAL_LINE);
        String firstLine = "Hello! I'm Duke";
        String secondLine = "What can I do for you?";
        System.out.printf("%1$" + (firstLine.length() + 5) + "s\n", firstLine);
        System.out.printf("%1$" + (secondLine.length() + 5) + "s\n", secondLine);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static void bye() {
        System.out.println(HORIZONTAL_LINE);
        String bye = "Bye. Hope to see you again soon!";
        System.out.printf("%1$" + (bye.length() + 5) + "s\n", bye);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printDoneTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        String heading = "Nice! I've marked this task as done:";
        System.out.printf("%1$" + (heading.length() + 5) + "s\n", heading);
        System.out.printf("%1$" + (task.toString().length() + 7) + "s\n",task.toString());
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printList(List<Task> list) {
        System.out.println(HORIZONTAL_LINE);
        String heading = "Here are the tasks in your list:";
        System.out.printf("%1$" + (heading.length() + 5) + "s\n", heading);
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            Task task = list.get(i);
            String output = index + "." + task.toString();
            System.out.printf("%1$" + (output.length() + 5) + "s\n", output);
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printTaskAdded(List<Task> list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        String firstLine = "Got it. I've added this task:";
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";
        System.out.printf("%1$" + (firstLine.length() + 5) + "s\n", firstLine);
        System.out.printf("%1$" + (task.toString().length() + 7) + "s\n", task.toString());
        System.out.printf("%1$"+ (lastLine.length() + 5) + "s\n", lastLine);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static String reformString(String[] arr, int start, int end) {
        String output = "";
        for (int i = start; i <= end && i < arr.length; i++) {
            output += arr[i] + " ";
        }
        return output.trim();
    }
}
