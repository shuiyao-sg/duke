/**
 * Encapsulates a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     * @param descprition
     */
    public ToDo(String descprition) {
        super(descprition);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
