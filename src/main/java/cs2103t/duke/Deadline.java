package cs2103t.duke;

import cs2103t.duke.date.MyDate;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

/**
 * Encapsulates deadline task.
 */
public class Deadline extends Task {

    protected MyDate by;

    /**
     * Constructs a cs2103t.duke.Deadline object
     *
     * @param description task description.
     * @param by          deadline date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = MyDate.genMyDate(by);
    }

    /**
     * Generates a cs2103t.duke.Deadline task from input String.
     *
     * @param s deadline date and time represented by String.
     * @return cs2103t.duke.Deadline task.
     */
    public static Deadline genDeadline(String s) {
        String[] newInputArray = s.split("/by");
        String des, by;
        try {
            des = newInputArray[0].trim();
            by = newInputArray[1].trim();
            return new Deadline(des, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Illegal input for deadline. "
                    + "Please key in 'deadline <task> /by <time>'");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Illegal input for time. Please key in the format "
                    + "'DD/MM/YYYY'[SPACE]'hhmm'");
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + by.toString();
    }

}
