package cs2103t.duke.command;

import cs2103t.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a find command to search for a keyword.
 */
public class FindCommand extends Command {
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";

    private String text;

    /**
     * Constructs a cs2103t.duke.command.FindCommand object.
     *
     * @param list input task list.
     * @param text text to find specified by user input.
     */
    public FindCommand(TaskList list, String text) {
        super(list);
        this.text = text;
    }

    public String execute() throws IOException {
        TaskList taskList = this.list.query(this.text);
        return FIND_MESSAGE + "\n" + taskList.toString();
    }
}
