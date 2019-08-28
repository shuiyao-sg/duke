import java.io.IOException;

public class ToDoCommand extends Command {

    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    private Storage storage;

    public ToDoCommand(TaskList list, String des) {
        super(list);
        super.task = new ToDo(des);
        this.storage = new Storage(FILE_PATH);
    }

    @Override
    public void execute() throws IOException {
        super.list.addTask(super.task);
        this.storage.appendText(super.task.toString());
        printTaskAdded(super.list, super.task);
    }

    public static void printTaskAdded(TaskList list, Task task) {
        System.out.println(HORIZONTAL_LINE);
        printTask(list, task, TASK_ADDED_MESSAGE);
    }

}
