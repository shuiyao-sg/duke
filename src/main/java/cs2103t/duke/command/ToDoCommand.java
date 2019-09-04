package cs2103t.duke.command;

import cs2103t.duke.Storage;
import cs2103t.duke.Task;
import cs2103t.duke.TaskList;
import cs2103t.duke.ToDo;

import java.io.IOException;

/**
 * Encapsulates a cs2103t.duke.ToDo command to create a cs2103t.duke.ToDo task.
 */
public class ToDoCommand extends Command {

    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    private Storage storage;

    /**
     * Constructs a cs2103t.duke.command.ToDoCommand object
     *
     * @param list task list.
     * @param des  task description.
     */
    public ToDoCommand(TaskList list, String des) {
        super(list);
        super.task = new ToDo(des);
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
        return "ToDoCommand";
    }

    private static void printTaskAdded(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        printTask(list, task, TASK_ADDED_MESSAGE);
    }
}
