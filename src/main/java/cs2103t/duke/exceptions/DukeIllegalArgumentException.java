package cs2103t.duke.exceptions;

/**
 * Represents IllegalArgumentException for cs2103t.duke.Duke. Thrown when encountered invalid user input.
 */
public class DukeIllegalArgumentException extends DukeException {

    /**
     * Constructs a DukeIllegalArgumentException object
     *
     * @param message exception message.
     */
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}
