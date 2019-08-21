import cs2103t.duke.exceptions.DukeException;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

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

            try {
                if (input.isBlank()) {
                    throw new DukeIllegalArgumentException("Empty user input is not allowed");
                }

                if (input.equals("bye")) {
                    bye();
                    sc.close();
                    break;
                } else if (input.equals("list")) {
                    printList(list);
                } else {
                    String[] inputArray = input.split(" ");
                    if (inputArray[0].equals("done")) {
                        try {
                            int index = Integer.parseInt(inputArray[1]) - 1;
                            Task task = list.get(index);
                            task.markAsDone();
                            printDoneTask(task);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeIllegalArgumentException("Please enter an integer after 'done'");
                        } catch (NumberFormatException e) {
                            throw new DukeIllegalArgumentException("Input is not an integer. "
                                    + "Please enter an integer after 'done'");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeIllegalArgumentException("Please input a number between 1 and "
                                    + list.size() + " (inclusive)");
                        }
                    } else if (inputArray[0].equals("delete")) {
                        try {
                            int index = Integer.parseInt(inputArray[1]) - 1;
                            Task task = list.get(index);
                            list.remove(index);
                            printTaskAdded(list, task);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeIllegalArgumentException("Please enter an integer after 'delete'");
                        } catch (NumberFormatException e) {
                            throw new DukeIllegalArgumentException("Input is not an integer. "
                                    + "Please enter an integer after 'delete'");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeIllegalArgumentException("Please input a number between 1 and "
                                    + list.size() + " (inclusive)");
                        }
                    } else if (inputArray[0].equals("todo")) {
                        String des = reformString(inputArray, 1, inputArray.length - 1);
                        Task task = new ToDo(des);
                        list.add(task);
                        printTaskAdded(list, task);
                    } else if (inputArray[0].equals("deadline")) {
                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
                        Task task = Deadline.genDeadline(newInput);
                        list.add(task);
                        printTaskAdded(list, task);
                    } else if (inputArray[0].equals("event")) {
                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
                        Task task = Event.genEvent(newInput);
                        list.add(task);
                        printTaskAdded(list, task);
                    } else {
                        String secondLine = "Permissible command: [list], [done], [todo], [deadline], [event], [bye]";
                        throw new DukeIllegalArgumentException("Illegal user input.\n"
                                + String.format("%1$" + (secondLine.length() + 5) + "s", secondLine));
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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
        printTask(list, task, firstLine);
    }

    private static void printTaskDeleted(List<Task> list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        String firstLine = "Noted. I've removed this task:";
        printTask(list, task, firstLine);
    }

    private static void printTask(List<Task> list, Task task, String firstLine) {
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";
        System.out.printf("%1$" + (firstLine.length() + 5) + "s\n", firstLine);
        System.out.printf("%1$" + (task.toString().length() + 7) + "s\n", task.toString());
        System.out.printf("%1$"+ (lastLine.length() + 5) + "s\n", lastLine);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static String reformString(String[] arr, int start, int end) {
        String output = "";
        for (int i = start; i <= end; i++) {
            output += arr[i] + " ";
        }
        return output.trim();
    }
}
