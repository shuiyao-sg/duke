package cs2103t.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        String input = "project meeting /at 01/09/2019 1400-1630";
        assertEquals("[E]" + "[" + "N" + "]" + " project meeting at: 1 SEPTEMBER 2019 14:00 to 16:30", Event.genEvent(input).toString());
    }
}
