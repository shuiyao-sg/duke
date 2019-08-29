import java.io.IOException;

/**
 * Encapsulates a done command to indicate that a task is done.
 */
public class DoneCommand extends Command {
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";

    private int index;
    private Storage storage;

    /**
     * Constructs a DoneCommand object
     *
     * @param list  input task list.
     * @param index index from user input.
     */
    public DoneCommand(TaskList list, int index) {
        super(list);
        this.index = index;
        super.task = this.list.getTask(index);
        this.storage = new Storage(FILE_PATH);
    }

    @Override
    public void execute() throws IOException {
        String initialTask = super.task.toString();
        super.task.markAsDone();
        storage.overwriteText(initialTask, super.task.toString());
        printTaskDone(super.task);
    }

    private static void printTaskDone(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(TASK_DONE_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.print(indentText(task.toString(), TEXT_INDENT_LEVEL));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }
}
