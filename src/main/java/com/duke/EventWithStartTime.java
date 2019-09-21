package com.duke;

import com.duke.exceptions.DukeIllegalArgumentException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventWithStartTime extends Event {

    protected LocalTime startTime;
    protected final DateTimeFormatter FORMAT_USER_INPUT_TIME = DateTimeFormatter.ofPattern("HHmm");
    protected final DateTimeFormatter FORMAT_FILE_TIME_STRING = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Constructs an Event object.
     *
     * @param description Task description.
     * @param date
     */
    public EventWithStartTime(String description, String date, String startTime) {
        super(description, date);
        this.startTime = LocalTime.parse(startTime, FORMAT_USER_INPUT_TIME);
    }

    EventWithStartTime(String description, String date, String startTime, boolean isFromFile) {
        super(description, date, isFromFile);
        if (isFromFile) {
            this.startTime = LocalTime.parse(startTime, FORMAT_FILE_TIME_STRING);
        } else {
            throw new DukeIllegalArgumentException("The input string is not from file");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.startTime.format(FORMAT_FILE_TIME_STRING);
    }
}
