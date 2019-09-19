package com.duke;

import com.duke.date.MyDate;
import com.duke.exceptions.DukeIllegalArgumentException;

/**
 * Encapsulates deadline task.
 */
public class Deadline extends Task {

    protected MyDate by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Task description.
     * @param by          Deadline date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = MyDate.genMyDate(by);
    }

    /**
     * Generates a Deadline task from input String.
     *
     * @param s Deadline date and time represented by String.
     * @return Deadline task.
     */
    public static Deadline genDeadline(String s) {
        String[] newInputArray = s.split("/by");
        String des = "";
        String by = "";
        try {
            des = newInputArray[0].trim();
            by = newInputArray[1].trim();
            return new Deadline(des, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input for deadline.\n"
                    + "You may key in one of the following:\n"
                    + "deadline <task> /by [dd/mm/yyyy]\n"
                    + "deadline <task> /by [dd/mm/yyyy hhmm]");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Invalid input for time.\n"
                    + "Please key in the format:\n"
                    + "dd/mm/yyyy hhmm");
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + by.toString();
    }
}
