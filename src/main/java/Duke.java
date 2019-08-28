import java.io.IOException;

public class Duke {
    private static final String INDENT_BY_FOUR = " " + " " + " " + " ";
    private static final String HORIZONTAL_LINE = INDENT_BY_FOUR
            + "____________________________________________________________";
    private static final String FILE_PATH = "F:/CS2103T/Duke/data/duke.txt";

    public static void main(String[] args) throws IOException {
            TextUi ui = new TextUi();
            ui.run();
    }


//        TextUi.greet();
//
//
//        Storage storage = new Storage(FILE_PATH);
//
//        System.out.println(HORIZONTAL_LINE);
//
//        TaskList list = storage.genTaskListFromFile();
//        storage.printFile();
//
//        System.out.println(HORIZONTAL_LINE);
//        System.out.println();
//
//        Scanner sc = new Scanner(System.in);
//
//
//        while (sc.hasNextLine()) {
//            String input = sc.nextLine();
//
//            try {
//                if (input.isBlank()) {
//                    throw new DukeIllegalArgumentException("Empty user input is not allowed");
//                }
//
//                if (input.equals("bye")) {
//                    TextUi.bye();
//                    sc.close();
//
//                    break;
//                } else if (input.equals("list")) {
//                    TextUi.printList(list);
//                } else {
//                    String[] inputArray = input.split(" ");
//                    if (inputArray[0].equals("done")) {
//                        try {
//                            int index = Integer.parseInt(inputArray[1]) - 1;
//                            //Task task = list.get(index);
//                            Task task = list.getTask(index);
//
//                            String initialTaskString = task.toString();
//
//                            task.markAsDone();
//                            TextUi.printTaskDone(task);
//
//                            storage.overwriteText(initialTaskString, task.toString());
//
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            throw new DukeIllegalArgumentException("Please enter an integer after 'done'");
//                        } catch (NumberFormatException e) {
//                            throw new DukeIllegalArgumentException("Input is not an integer. "
//                                    + "Please enter an integer after 'done'");
//                        } catch (IndexOutOfBoundsException e) {
//                            throw new DukeIllegalArgumentException("Please input a number between 1 and "
//                                    + list.size() + " (inclusive)");
//                        }
//                    } else if (inputArray[0].equals("delete")) {
//                        try {
//                            int index = Integer.parseInt(inputArray[1]) - 1;
//                            Task task = list.getTask(index);
//                            list.deleteTask(index);
//                            TextUi.printTaskDeleted(list, task);
//
//                            //modify file
//                            storage.deleteText(task.toString());
//
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            throw new DukeIllegalArgumentException("Please enter an integer after 'delete'");
//                        } catch (NumberFormatException e) {
//                            throw new DukeIllegalArgumentException("Input is not an integer. "
//                                    + "Please enter an integer after 'delete'");
//                        } catch (IndexOutOfBoundsException e) {
//                            throw new DukeIllegalArgumentException("Please input a number between 1 and "
//                                    + list.size() + " (inclusive)");
//                        }
//                    } else if (inputArray[0].equals("todo")) {
//                        String des = reformString(inputArray, 1, inputArray.length - 1);
//                        Task task = new ToDo(des);
//
//                        list.addTask(task);
//                        TextUi.printTaskAdded(list, task);
//
//                        // append to file
//                        storage.appendText(task.toString());
//
//
//                    } else if (inputArray[0].equals("deadline")) {
//                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
//                        Task task = Deadline.genDeadline(newInput);
//
//                        list.addTask(task);
//                        TextUi.printTaskAdded(list, task);
//
//                        // append to file
//                        storage.appendText(task.toString());
//
//
//                    } else if (inputArray[0].equals("event")) {
//                        String newInput = reformString(inputArray, 1, inputArray.length - 1);
//                        Task task = Event.genEvent(newInput);
//
//                        list.addTask(task);
//                        TextUi.printTaskAdded(list, task);
//
//                        // append to file
//                        storage.appendText(task.toString());
//
//
//                    } else {
//                        String secondLine = "Permissible command: [list], [done], [todo], [deadline], [event], [bye]";
//                        throw new DukeIllegalArgumentException("Illegal user input.\n"
//                                + String.format("%1$" + (secondLine.length() + 5) + "s", secondLine));
//                    }
//                }
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }


    /*
    private static String reformString(String[] arr, int start, int end) {
        String output = "";
        for (int i = start; i <= end; i++) {
            output += arr[i] + " ";
        }
        return output.trim();
    }

     */
}
