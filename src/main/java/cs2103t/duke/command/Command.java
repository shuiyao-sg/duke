package cs2103t.duke.command;

import cs2103t.duke.Task;
import cs2103t.duke.TaskList;

import java.io.IOException;

/**
 * Represents command to be executed by cs2103t.duke.Duke
 */
public abstract class Command {
    protected static final int TEXT_INDENT_LEVEL = 5;
    protected static final String INDENT_BY_FOUR = " " + " " + " " + " ";
    protected static final String HORIZONTAL_LINE = INDENT_BY_FOUR
            + "____________________________________________________________";
    protected static final String FILE_PATH = "src/data/duke.txt";

    protected TaskList list;
    protected Task task;

    /**
     * Constructs a cs2103t.duke.command.Command object
     *
     * @param taskList input task list.
     */
    public Command(TaskList taskList) {
        this.list = taskList;
    }

    public Command() {
    }

    public abstract String execute() throws IOException;

    protected static String indentText(String input, int indentLevel) {
        return String.format("%1$" + (input.length() + indentLevel) + "s\n", input);
    }

    protected String stringifyTask(TaskList list , Task task, String firstLine) {
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";
        return firstLine + "\n" + task.toString() + "\n" + lastLine;
    }

    /**
     * Checks whether the cs2103t.duke.command.Command is a cs2103t.duke.command.ByeCommand.
     *
     * @return true if it is a cs2103t.duke.command.ByeCommand, false otherwise
     */
    public boolean isByeCommand() {
        return false;
    }
}
