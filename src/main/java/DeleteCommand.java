import java.io.IOException;

/**
 * Encapsulates a delete command to delete task from task list.
 */
public class DeleteCommand extends Command {

    private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";

    private int index;
    private Storage storage;

    /**
     * Constructs a DeleteCommand object
     * @param list
     * @param index
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
