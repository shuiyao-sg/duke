package cs2103t.duke;

import cs2103t.duke.command.Command;
import cs2103t.duke.exceptions.DukeException;

import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

//    /**
//     * Starts the program.
//     *
//     * @param args arguments passed in.
//     */
//    public static void main(String[] args) {
//        TextUi ui = new TextUi();
//        ui.run();
//    }

    private static final String FILE_PATH = "F:/CS2103T/Duke/data/duke.txt";
    private static Parser parser;

    Storage storage;
    TaskList list;
    //Parser parser;

    public Duke() {
        storage = new Storage(FILE_PATH);
        list = storage.genTaskListFromFile();
        parser = new Parser(list);
    }

    public static String getResponse(String text) {
        try {
            Command command = parser.parseCommand(text);

            return command.execute();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
