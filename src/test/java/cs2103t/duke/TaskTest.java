package cs2103t.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDoneTest() {
        Task task = new Task("read");
        assertEquals("[" + "N" + "]", task.getStatusIcon());
        task.markAsDone();
        assertEquals("[" + "Y" + "]", task.getStatusIcon());
    }
}
