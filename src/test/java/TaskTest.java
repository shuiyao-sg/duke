import cs2103t.duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDoneTest() {
        Task task = new Task("read");
        assertEquals("[" + "\u2718" + "]", task.getStatusIcon());
        task.markAsDone();
        assertEquals("[" + "\u2713" + "]", task.getStatusIcon());
    }
}
