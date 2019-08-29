/**
 * Encapsulates a find command to search for a keyword.
 */
public class FindCommand extends Command {
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";

    private String text;

    /**
     * Constructs a FindCommand object.
     *
     * @param list input task list.
     * @param text text to find specified by user input.
     */
    public FindCommand(TaskList list, String text) {
        super(list);
        this.text = text;
    }

    @Override
    public void execute() {
        TaskList taskList = this.list.query(this.text);
        printList(taskList);
    }

    private static void printList(TaskList list) {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(FIND_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.println(list);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }
}
