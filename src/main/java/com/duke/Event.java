package com.duke;

import com.duke.date.MyDate;
import com.duke.exceptions.DukeIllegalArgumentException;

/**
 * Encapsulates an Event task.
 */
public class Event extends Task {

    private MyDate at;

    /**
     * Constructs an Event object.
     *
     * @param description Task description.
     * @param at          Event date and time represented by String.
     */
    Event(String description, String at) {
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
        String des = "";
        String at = "";
        try {
            des = newInputArray[0].trim();
            at = newInputArray[1].trim();
            return new Event(des, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input for event.\n"
                    + "You may key in one of the following:\n"
                    + "event <task> /at [dd/mm/yyyy]\n"
                    + "event <task> /at [dd/mm/yyyy hhmm]\n"
                    + "event <task> /at [dd/mm/yyyy hhmm-hhmm]");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Invalid input for time.\n"
                    + "You may try one of these formats:\n"
                    + "dd/mm/yyyy hhmm\n"
                    + "dd/mm/yyyy hhmm-hhmm");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + at.toString();
    }
}
