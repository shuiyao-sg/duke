import cs2103t.duke.exceptions.DukeIllegalArgumentException;

/**
 * Encapsulates a parser to deal with making sense of the user command.
 */
public class Parser {
    private TaskList list;

    /**
     * Constructs a Parser object
     *
     * @param list
     */
    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Parses user's command to instructions for the bot to execute.
     *
     * @param input command from user input.
     * @return Command command to be executed.
     */
    public Command parseCommand(String input) {
        if (input.isBlank()) {
            throw new DukeIllegalArgumentException("Empty user input is not allowed");
        }

        if (input.equals("bye")) {
            return new ByeCommand();
        }

        if (input.equals("list")) {
            return new ListCommand(this.list);
        }

        String[] inputArray = input.split(" ");

        if (inputArray[0].equals("done")) {
            try {
                int index = Integer.parseInt(inputArray[1]) - 1;
                return new DoneCommand(this.list, index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeIllegalArgumentException("Please enter an integer after 'done'");
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException("Input is not an integer. "
                        + "Please enter an integer after 'done'");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeIllegalArgumentException("Please input a number between 1 and "
                        + this.list.size() + " (inclusive)");
            }
        }

        if (inputArray[0].equals("delete")) {
            try {
                int index = Integer.parseInt(inputArray[1]) - 1;
                return new DeleteCommand(this.list, index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeIllegalArgumentException("Please enter an integer after 'delete'");
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException("Input is not an integer. "
                        + "Please enter an integer after 'delete'");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeIllegalArgumentException("Please input a number between 1 and "
                        + list.size() + " (inclusive)");
            }
        }

        if (inputArray[0].equals("todo")) {
            String des = reformString(inputArray, 1, inputArray.length - 1);
            return new ToDoCommand(this.list, des);
        }

        if (inputArray[0].equals("deadline")) {
            String s = reformString(inputArray, 1, inputArray.length - 1);
            return new DeadlineCommand(this.list, s);
        }

        if (inputArray[0].equals("event")) {
            String s = reformString(inputArray, 1, inputArray.length - 1);
            return new EventCommand(this.list, s);
        }

        if (inputArray[0].equals("find")) {
            String s = reformString(inputArray, 1, inputArray.length - 1);
            return new FindCommand(this.list, s);
        }

        String secondLine = "Permissible command: [list], [done], [todo], [deadline], [event], [bye]";
        throw new DukeIllegalArgumentException("Illegal user input.\n"
                + String.format("%1$" + (secondLine.length() + 5) + "s", secondLine));
    }

    private static String reformString(String[] arr, int start, int end) {
        String output = "";
        for (int i = start; i <= end; i++) {
            output += arr[i] + " ";
        }
        return output.trim();
    }
}
