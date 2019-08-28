import cs2103t.duke.exceptions.DukeException;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

import java.io.IOException;
import java.util.Scanner;

public class TextUi {
    private static final int TEXT_INDENT_LEVEL = 5;
    private static final String INDENT_BY_FOUR = " " + " " + " " + " ";
    private static final String HORIZONTAL_LINE = INDENT_BY_FOUR
            + "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String FILE_PATH = "F:/CS2103T/Duke/data/duke.txt";
    private static final String GREET_MESSAGE_FRIST_LINE = "Hello! I'm Duke";
    private static final String GREET_MESSAGE_SECOND_LINE = "What can I do for you?";
    //private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    //private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    //private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";
    //private static final String LIST_MESSAGE = "Here are the tasks in your list:";

    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(GREET_MESSAGE_FRIST_LINE, TEXT_INDENT_LEVEL));
        System.out.print(indentText(GREET_MESSAGE_SECOND_LINE, TEXT_INDENT_LEVEL));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    /*
    public static void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(BYE_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.println(HORIZONTAL_LINE);
    }

     */

    private static String indentText(String input, int indentLevel) {
        return String.format("%1$" + (input.length() + indentLevel) + "s\n", input);
    }

    /*
    public static void printTaskDone(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(TASK_DONE_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.print(indentText(task.toString(), TEXT_INDENT_LEVEL));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

     */

    /*
    public static void printList(TaskList list) {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(LIST_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.println(list);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

     */


    public static void printTaskAdded(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        printTask(list, task, TASK_ADDED_MESSAGE);
    }



    /*
    public static void printTaskDeleted(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        printTask(list, task, TASK_DELETED_MESSAGE);
    }
    */



    private static void printTask(TaskList list, Task task, String firstLine) {
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";

        System.out.print(indentText(firstLine, TEXT_INDENT_LEVEL));
        System.out.print(indentText(task.toString(), TEXT_INDENT_LEVEL + 2));
        System.out.print(indentText(lastLine, TEXT_INDENT_LEVEL));
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

    public void run() {

        TextUi.greet();


        Storage storage = new Storage(FILE_PATH);

        System.out.println(HORIZONTAL_LINE);

        TaskList list = storage.genTaskListFromFile();
        storage.printFile();

        System.out.println(HORIZONTAL_LINE);
        System.out.println();

        Scanner sc = new Scanner(System.in);

        Parser parser = new Parser(list);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            try {
                if (input.isBlank()) {
                    throw new DukeIllegalArgumentException("Empty user input is not allowed");
                }

                if (input.equals("bye")) {
                    Command command = new ByeCommand();
                    command.execute();
                    //TextUi.bye();
//                    sc.close();

                    if (command.isByeCommand()) {
                        sc.close();
                        break;
                    }

                } else if (input.equals("list")) {
                    Command command = new ListCommand(list);
                    command.execute();
                    //TextUi.printList(list);
                } else {
                    String[] inputArray = input.split(" ");
                    if (inputArray[0].equals("done")) {
                        try {
                            int index = Integer.parseInt(inputArray[1]) - 1;
                            Command command = new DoneCommand(list, index);
                            command.execute();
                            /*
                            Task task = list.getTask(index);

                            String initialTaskString = task.toString();

                            task.markAsDone();
                            TextUi.printTaskDone(task);

                            storage.overwriteText(initialTaskString, task.toString());
                            */
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
                            Command command = new DeleteCommand(list, index);
                            command.execute();

                            /*
                            Task task = list.getTask(index);
                            list.deleteTask(index);
                            TextUi.printTaskDeleted(list, task);

                            //modify file
                            storage.deleteText(task.toString());
                            */
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
                        Command command = new ToDoCommand(list, des);
                        command.execute();
                        /*
                        Task task = new ToDo(des);

                        list.addTask(task);
                        TextUi.printTaskAdded(list, task);

                        // append to file
                        storage.appendText(task.toString());
                        */

                    } else if (inputArray[0].equals("deadline")) {
                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
                        Command command = new DeadlineCommand(list, newInput);
                        command.execute();
                        /*
                        Task task = Deadline.genDeadline(newInput);

                        list.addTask(task);
                        TextUi.printTaskAdded(list, task);

                        // append to file
                        storage.appendText(task.toString());
                        */

                    } else if (inputArray[0].equals("event")) {
                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
                        Command command = new EventCommand(list, newInput);
                        command.execute();
                        /*
                        Task task = Event.genEvent(newInput);

                        list.addTask(task);
                        TextUi.printTaskAdded(list, task);

                        // append to file
                        storage.appendText(task.toString());
                        */

                    } else {
                        String secondLine = "Permissible command: [list], [done], [todo], [deadline], [event], [bye]";
                        throw new DukeIllegalArgumentException("Illegal user input.\n"
                                + String.format("%1$" + (secondLine.length() + 5) + "s", secondLine));
                    }
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
