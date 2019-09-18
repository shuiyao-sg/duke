package com.duke.exceptions;

/**
 * Represents DukeException in general.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructs a DukeException object.
     *
     * @param message exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
