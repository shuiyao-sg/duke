public class ByeCommand extends Command {
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public void execute() {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(BYE_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public boolean isByeCommand() {
        return true;
    }
}
