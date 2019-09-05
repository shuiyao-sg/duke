package com.duke;

/**
 * Encapsulates a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     *
     * @param description task description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
