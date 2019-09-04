package cs2103t.duke;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    /**
     * Starts the program.
     *
     * @param args arguments passed in.
     */
    public static void main(String[] args) {
        TextUi ui = new TextUi();
        ui.run();
    }

    public static String getResponse(String text) {
        return text;
    }
}
