import cs2103t.duke.exceptions.DukeException;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String INDENT_BY_FOUR = " " + " " + " " + " ";
    private static final String HORIZONTAL_LINE = INDENT_BY_FOUR
            + "____________________________________________________________";
    private static final String FILE_PATH = "F:/CS2103T/Duke/data/duke.txt";

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

//        String filePath = "/data/duke.txt";
//        FileWriter fw = new FileWriter(FILE_PATH, true);

        //initiate task list
//        List<Task> list = new ArrayList<>();
        TaskList list = new TaskList();

        // read input file
        Scanner fileScanner = new Scanner(new File(FILE_PATH));

        System.out.println(HORIZONTAL_LINE);
        String heading = "File successfully loaded. Remaining tasks: ";
        System.out.printf("%1$" + (heading.length() + 5) + "s\n", heading);

        while (fileScanner.hasNextLine()) {
            String nextLineOfFile = fileScanner.nextLine().trim();
            Task task = genTaskFromFile(nextLineOfFile);
            //list.add(task);
            list.addTask(task);
            //System.out.println(nextLineOfFile);
            System.out.printf("%1$" + (nextLineOfFile.length() + 5) + "s\n", nextLineOfFile);
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println();

        Scanner sc = new Scanner(System.in);


        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            try {
                if (input.isBlank()) {
                    throw new DukeIllegalArgumentException("Empty user input is not allowed");
                }

                if (input.equals("bye")) {
                    bye();
                    sc.close();
//                    fw.close();
                    break;
                } else if (input.equals("list")) {
                    printList(list);
                } else {
                    String[] inputArray = input.split(" ");
                    if (inputArray[0].equals("done")) {
                        try {
                            int index = Integer.parseInt(inputArray[1]) - 1;
                            //Task task = list.get(index);
                            Task task = list.getTask(index);

                            String initialTaskString = task.toString();

                            task.markAsDone();
                            printDoneTask(task);

                            //modify file
                            List<String> inputList = Files.readAllLines(Paths.get(FILE_PATH));

                            String output = "";

                            for (String s : inputList) {
                                if (s.equals(initialTaskString)) {
                                    output += task.toString() + System.lineSeparator();
                                } else {
                                    output += s + System.lineSeparator();
                                }
                            }

                            FileWriter fw = new FileWriter(FILE_PATH, false);
                            fw.write(output);
                            fw.close();


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
//                            Task task = list.get(index);
//                            list.remove(index);
//                            printTaskDeleted(list, task);
                            Task task = list.getTask(index);
                            list.deleteTask(index);
                            printTaskDeleted(list, task);

                            /*
                            need to add file output
                             */
                            //modify file
                            List<String> inputList = Files.readAllLines(Paths.get(FILE_PATH));

                            String output = "";

                            for (String s : inputList) {
                                if (!s.equals(task.toString())) {
                                    output += s + System.lineSeparator();
                                }
                            }

                            FileWriter fw = new FileWriter(FILE_PATH, false);
                            fw.write(output);
                            fw.close();


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
                        //list.add(task);
                        list.addTask(task);
                        printTaskAdded(list, task);

                        // append to file
                        FileWriter fw = new FileWriter(FILE_PATH, true);
                        fw.write(task.toString() + System.lineSeparator());
                        fw.close();

                    } else if (inputArray[0].equals("deadline")) {
                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
                        Task task = Deadline.genDeadline(newInput);
                        //list.add(task);
                        list.addTask(task);
                        printTaskAdded(list, task);

                        // append to file
                        FileWriter fw = new FileWriter(FILE_PATH, true);
                        fw.write(task.toString() + System.lineSeparator());
                        fw.close();

                    } else if (inputArray[0].equals("event")) {
                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
                        Task task = Event.genEvent(newInput);
                        //list.add(task);
                        list.addTask(task);
                        printTaskAdded(list, task);

                        // append to file
                        FileWriter fw = new FileWriter(FILE_PATH, true);
                        fw.write(task.toString() + System.lineSeparator());
                        fw.close();

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

    /*
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
    */
    private static void printList(TaskList list) {
        System.out.println(HORIZONTAL_LINE);
        String heading = "Here are the tasks in your list:";
        System.out.printf("%1$" + (heading.length() + 5) + "s\n", heading);
        System.out.println(list);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }


    private static void printTaskAdded(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        String firstLine = "Got it. I've added this task:";
        printTask(list, task, firstLine);
    }

    private static void printTaskDeleted(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        String firstLine = "Noted. I've removed this task:";
        printTask(list, task, firstLine);
    }

    /*
    private static void printTask(List<Task> list, Task task, String firstLine) {
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";
        System.out.printf("%1$" + (firstLine.length() + 5) + "s\n", firstLine);
        System.out.printf("%1$" + (task.toString().length() + 7) + "s\n", task.toString());
        System.out.printf("%1$"+ (lastLine.length() + 5) + "s\n", lastLine);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

     */

    private static void printTask(TaskList list, Task task, String firstLine) {
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

    private static Task genTaskFromFile(String s) {
        char taskType = s.charAt(1);
        String isDone = s.charAt(4) + "";

        int index = s.indexOf("] ");
        String taskContent = s.substring(index + 1).trim();

        switch (taskType) {
            case 'T':
                Task todo = new ToDo(taskContent);
                if (isDone.equals("\u2713")) {
                    todo.markAsDone();
                }
                return todo;
            case 'D':
                String[] deadlineContentArray = taskContent.split("by:");
                Task deadline = new Deadline(deadlineContentArray[0].trim(), deadlineContentArray[1].trim());
                if (isDone.equals("\u2713")) {
                    deadline.markAsDone();
                }
                return deadline;
            case 'E':
                String[] eventContentArray = taskContent.split("at:");
                Task event = new Event(eventContentArray[0].trim(), eventContentArray[1].trim());
                if (isDone.equals("\u2713")) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }
}
