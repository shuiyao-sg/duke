/**
 * Encapsulates a list command to list out all the tasks.
 */
public class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";

    /**
     * Constructs a ListCommand object.
     *
     * @param list input task list.
     */
    public ListCommand(TaskList list) {
        super(list);
    }

    @Override
    public void execute() {
        printList(super.list);
    }

    private static void printList(TaskList list) {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(LIST_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.println(list);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }
}
