import cs2103t.duke.exceptions.DukeException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates a text UI to deal with interactions with the user.
 */
public class TextUi {
    private static final int TEXT_INDENT_LEVEL = 5;
    private static final String INDENT_BY_FOUR = " " + " " + " " + " ";
    private static final String HORIZONTAL_LINE = INDENT_BY_FOUR
            + "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String FILE_PATH = "F:/CS2103T/Duke/data/duke.txt";
    private static final String GREET_MESSAGE_FRIST_LINE = "Hello! I'm Duke";
    private static final String GREET_MESSAGE_SECOND_LINE = "What can I do for you?";

    private static void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(GREET_MESSAGE_FRIST_LINE, TEXT_INDENT_LEVEL));
        System.out.print(indentText(GREET_MESSAGE_SECOND_LINE, TEXT_INDENT_LEVEL));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static String indentText(String input, int indentLevel) {
        return String.format("%1$" + (input.length() + indentLevel) + "s\n", input);
    }

    /**
     * Starts the text UI.
     */
    public void run() {
        TextUi.greet();

        Storage storage = new Storage(FILE_PATH);
        System.out.println(HORIZONTAL_LINE);

        TaskList list = storage.genTaskListFromFile();
        storage.printFile();

        System.out.println(HORIZONTAL_LINE);
        System.out.println();

        Scanner sc = new Scanner(System.in);

        Parser parser = new Parser(list);

        while (sc.hasNextLine()) {
            String fileInput = sc.nextLine();
            try {
                Command command = parser.parseCommand(fileInput);
                command.execute();

                if (command.isByeCommand()) {
                    sc.close();
                    break;
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
