import cs2103t.duke.date.MyDate;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

/**
 * Encapsulates an Event task.
 */
public class Event extends Task {

    protected MyDate at;

    /**
     * Constructs an Event object
     *
     * @param description task description.
     * @param at          event date and time represented by String.
     */
    public Event(String description, String at) {
        super(description);
        this.at = MyDate.genMyDate(at);
    }

    /**
     * Generates an Event task from input String.
     *
     * @param s String representation of event.
     * @return Event task.
     */
    public static Event genEvent(String s) {
        String[] newInputArray = s.split("/at");
        String des, at;
        try {
            des = newInputArray[0].trim();
            at = newInputArray[1].trim();
            return new Event(des, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Illegal input for event. "
                    + "Please key in 'event <task> /at <time>'");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Illegal input for time. Please key in the format "
                    + "'DD/MM/YYYY'[SPACE]'hhmm-hhmm'");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + at.toString();
    }
}
