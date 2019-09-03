import cs2103t.duke.Deadline;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void genDeadline_illegalFormat_exceptionThrown() {
        try {
            String input = "return book BY 02/03/2019";
            assertEquals("[D]" + "[" + "\u2718" + "]" + " return book by: 2 MARCH 2019", Deadline.genDeadline(input));
            fail();
        } catch (DukeIllegalArgumentException e) {
            String horizontalLine = " " + " " + " " + " " + "____________________________________________________________";
            String message = "Illegal input for deadline. " + "Please key in 'deadline <task> /by <time>'";
            String error = horizontalLine + "\n" + String.format("%1$" + (message.length() + 5) + "s\n",
                    message) + horizontalLine + "\n";
            assertEquals(error, e.getMessage());
        }
    }
}
