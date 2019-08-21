package cs2103t.duke.exceptions;

public class DukeException extends RuntimeException {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String horizontalLine = " " + " " + " " + " " + "____________________________________________________________";
        return horizontalLine + "\n" + String.format("%1$" + (super.getMessage().length() + 5) + "s\n",
                super.getMessage()) + horizontalLine + "\n";
    }
}
