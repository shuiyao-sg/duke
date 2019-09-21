package com.duke.command;

import com.duke.Storage;
import com.duke.task.Event;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates an event command to create an event task.
 */
public class EventCommand extends Command {
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    private Storage storage;

    /**
     * Constructs an EventCommand object.
     *
     * @param list  Input task list.
     * @param input Command from user input.
     */
    public EventCommand(TaskList list, String input) {
        super(list);
        super.task = Event.genEvent(input);
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Executes the command.
     *
     * @return Output shown to user.
     * @throws IOException If file is not found or cannot be read.
     */
    public String execute() throws IOException {
        super.list.addTask(super.task);
        this.storage.appendText(super.task.toString());
        return stringifyTask(super.list, super.task, TASK_ADDED_MESSAGE);
    }
}
