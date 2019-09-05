package cs2103t.duke.command;

import cs2103t.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a list command to list out all the tasks.
 */
public class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";

    /**
     * Constructs a cs2103t.duke.command.ListCommand object.
     *
     * @param list input task list.
     */
    public ListCommand(TaskList list) {
        super(list);
    }

    public String execute() throws IOException {
        return LIST_MESSAGE + "\n" + list.toString();
    }
}
