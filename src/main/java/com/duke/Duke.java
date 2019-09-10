package com.duke;

import com.duke.command.Command;
import com.duke.exceptions.DukeException;

import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private static final String FILE_PATH = "src/data/duke.txt";
    private static Parser parser;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        Storage storage = new Storage(FILE_PATH);
        TaskList list = storage.genTaskListFromFile();
        parser = new Parser(list);
    }

    /**
     * Gets Duke's response to user input.
     *
     * @param text user input text.
     * @return response by Duke.
     */
    public static String getResponse(String text) {
        try {
            Command command = parser.parseCommand(text);
            return command.execute();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
