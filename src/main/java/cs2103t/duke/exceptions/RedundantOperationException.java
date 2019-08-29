package cs2103t.duke.exceptions;

/**
 * Represents RedundantOperationException. Thrown when there is redundant operation
 */
public class RedundantOperationException extends DukeException {

    /**
     * Constructs a RedundantOperationException object
     *
     * @param message exception message.
     */
    public RedundantOperationException(String message) {
        super(message);
    }
}
