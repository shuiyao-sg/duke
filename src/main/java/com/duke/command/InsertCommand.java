package com.duke.command;

import com.duke.Storage;
import com.duke.Task;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates an insert command to insert task to a specific position.
 */
public class InsertCommand extends Command {
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private int position;
    private Storage storage;

    /**
     * Constructs an InsertCommand object.
     *
     * @param position the position where the task is inserted.
     * @param taskList the task list to be modified.
     * @param task     the task to be inserted.
     */
    public InsertCommand(int position, TaskList taskList, Task task) {
        super(taskList);
        super.task = task;
        this.position = position;
        this.storage = new Storage(FILE_PATH);
    }

    @Override
    public String execute() throws IOException {
        super.list.addTask(position, super.task);
        this.storage.insertText(position, super.task.toString());
        return stringifyTask(super.list, super.task, TASK_ADDED_MESSAGE);
    }
}
