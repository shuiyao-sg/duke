package com.duke;

import com.duke.exceptions.DukeIllegalArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private final DateTimeFormatter FORMAT_USER_INPUT_WITH_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter FORMAT_FILE_STRING = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
    private final static String INVALID_INPUT_ERROR_MESSAGE = "Invalid input for deadline.\n"
            + "You may key in one of the following with valid values:\n"
            + "deadline <task> /by [dd/MM/yyyy]\n"
            + "deadline <task> /by [dd/MM/yyyy HHmm]";

    /**
     * Constructs a Deadline object.
     *
     * @param description Task description.
     * @param by          Deadline date.
     */
    private Deadline(String description, String by) {
        super(description);
        //this.by = MyDate.genMyDate(by);
        try {
            this.by = LocalDateTime.parse(by, FORMAT_USER_INPUT_WITH_TIME);
        } catch (DateTimeParseException e) {
            this.by = LocalDateTime.parse(by + " 2359", FORMAT_USER_INPUT_WITH_TIME);
        }
    }

    private Deadline(String description, String by, boolean isFromFile) {
        super(description);
        if (isFromFile) {
            this.by = LocalDateTime.parse(by, FORMAT_FILE_STRING);
        } else {
            throw new DukeIllegalArgumentException("The input string is not from file");
        }

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
            throw new DukeIllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            if (e.getMessage().contains("Invalid value")) {
                throw new DukeIllegalArgumentException(e.getMessage());
            } else {
                throw new DukeIllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
            }
        }
    }

    static Deadline genDeadlineFromFile(String description, String by) {
        return new Deadline(description, by, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + by.format(FORMAT_FILE_STRING);
    }
}
