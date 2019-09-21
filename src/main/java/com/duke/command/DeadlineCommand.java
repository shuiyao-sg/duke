package com.duke.command;

import com.duke.Storage;
import com.duke.task.Deadline;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a deadline command to create a deadline task.
 */
public class DeadlineCommand extends Command {
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    private Storage storage;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param list  Input task list.
     * @param input Task description and date specified by user input.
     */
    public DeadlineCommand(TaskList list, String input) {
        super(list);
        super.task = Deadline.genDeadline(input);
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
