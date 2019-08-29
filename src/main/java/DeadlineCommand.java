import java.io.IOException;

/**
 * Encapsulates a deadline command to create a deadline task.
 */
public class DeadlineCommand extends Command {
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    private Storage storage;

    /**
     * Constructs a DeadlineCommand object
     *
     * @param list  input task list.
     * @param input task description and date specified by user input.
     */
    public DeadlineCommand(TaskList list, String input) {
        super(list);
        super.task = Deadline.genDeadline(input);
        this.storage = new Storage(FILE_PATH);
    }

    @Override
    public void execute() throws IOException {
        super.list.addTask(super.task);
        this.storage.appendText(super.task.toString());
        printTaskAdded(super.list, super.task);
    }

    private static void printTaskAdded(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        printTask(list, task, TASK_ADDED_MESSAGE);
    }
}
