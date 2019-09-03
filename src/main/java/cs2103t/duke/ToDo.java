package cs2103t.duke;

/**
 * Encapsulates a cs2103t.duke.ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a cs2103t.duke.ToDo object.
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
