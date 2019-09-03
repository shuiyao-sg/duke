package cs2103t.duke.command;

import cs2103t.duke.Storage;
import cs2103t.duke.Task;
import cs2103t.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a delete command to delete task from task list.
 */
public class DeleteCommand extends Command {

    private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";

    private int index;
    private Storage storage;

    /**
     * Constructs a cs2103t.duke.command.DeleteCommand object
     *
     * @param list  input task list.
     * @param index index from user input.
     */
    public DeleteCommand(TaskList list, int index) {
        super(list);
        this.index = index;
        super.task = this.list.getTask(index);
        this.storage = new Storage(FILE_PATH);
    }

    @Override
    public void execute() throws IOException {
        super.list.deleteTask(index);
        storage.deleteText(super.task.toString());
        printTaskDeleted(super.list, super.task);
    }

    private static void printTaskDeleted(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        printTask(list, task, TASK_DELETED_MESSAGE);
    }
}
