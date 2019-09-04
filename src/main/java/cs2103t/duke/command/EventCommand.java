package cs2103t.duke.command;

import cs2103t.duke.Event;
import cs2103t.duke.Storage;
import cs2103t.duke.Task;
import cs2103t.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates an event command to create an event task.
 */
public class EventCommand extends Command {
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    private Storage storage;

    /**
     * Constructs an cs2103t.duke.command.EventCommand object.
     *
     * @param list  input task list.
     * @param input command from user input.
     */
    public EventCommand(TaskList list, String input) {
        super(list);
        super.task = Event.genEvent(input);
        this.storage = new Storage(FILE_PATH);
    }

    /*
    @Override
    public void execute() throws IOException {
        super.list.addTask(super.task);
        this.storage.appendText(super.task.toString());
        printTaskAdded(super.list, super.task);
    }

     */

    public String execute() throws IOException {
        return "EventCommand";
    }

    private static void printTaskAdded(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        printTask(list, task, TASK_ADDED_MESSAGE);
    }
}
