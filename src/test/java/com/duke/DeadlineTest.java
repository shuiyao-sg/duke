package com.duke;

import com.duke.exceptions.DukeIllegalArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void genDeadline_illegalFormat_exceptionThrown() {
        try {
            String input = "return book BY 02/03/2019";
            assertEquals("[D]" + "[" + "N" + "]" + " return book by: 2 MARCH 2019", Deadline.genDeadline(input));
            fail();
        } catch (DukeIllegalArgumentException e) {
            String error = "Invalid input for deadline.\n"
                    + "You may key in one of the following:\n"
                    + "deadline <task> /by [dd/mm/yyyy]\n"
                    + "deadline <task> /by [dd/mm/yyyy hhmm]";
            assertEquals(error, e.getMessage());
        }
    }
}
