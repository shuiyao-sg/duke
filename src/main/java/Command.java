import java.io.IOException;

public abstract class Command {
    protected static final int TEXT_INDENT_LEVEL = 5;
    protected static final String INDENT_BY_FOUR = " " + " " + " " + " ";
    protected static final String HORIZONTAL_LINE = INDENT_BY_FOUR
            + "____________________________________________________________";
    protected static final String FILE_PATH = "F:/CS2103T/Duke/data/duke.txt";

    protected TaskList list;
    protected Task task;

    public Command(TaskList taskList) {
        this.list = taskList;
    }

    public Command() {}

    public abstract void execute() throws IOException;

    protected static String indentText(String input, int indentLevel) {
        return String.format("%1$" + (input.length() + indentLevel) + "s\n", input);
    }

    protected static void printTask(TaskList list, Task task, String firstLine) {
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";

        System.out.print(indentText(firstLine, TEXT_INDENT_LEVEL));
        System.out.print(indentText(task.toString(), TEXT_INDENT_LEVEL + 2));
        System.out.print(indentText(lastLine, TEXT_INDENT_LEVEL));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public boolean isByeCommand() {
        return false;
    }
}
