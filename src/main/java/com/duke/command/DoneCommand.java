package com.duke.command;

import com.duke.Storage;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a done command to indicate that a task is done.
 */
public class DoneCommand extends Command {
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";

    private int index;
    private Storage storage;

    /**
     * Constructs a DoneCommand object.
     *
     * @param list  input task list.
     * @param index index from user input.
     */
    public DoneCommand(TaskList list, int index) {
        super(list);
        this.index = index;
        super.task = this.list.getTask(index);
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Executes the command.
     *
     * @return output shown to user.
     * @throws IOException throws IOException when file not found.
     */
    public String execute() throws IOException {
        String initialTask = super.task.toString();
        super.task.markAsDone();
        storage.overwriteText(initialTask, super.task.toString());
        return TASK_DONE_MESSAGE + "\n" + task.toString();
    }
}
