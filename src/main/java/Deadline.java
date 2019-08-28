import cs2103t.duke.date.MyDate;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

/**
 * Encapsulates deadline task.
 */
public class Deadline extends Task {

    protected MyDate by;

    /**
     * Constructs a Deadline object
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = MyDate.genMyDate(by);
    }

    /**
     * Generates a Deadline task from input String.
     * @param s
     * @return Deadline task
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
