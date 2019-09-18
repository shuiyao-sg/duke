package com.duke.command;

import com.duke.Storage;
import com.duke.TaskList;
import com.duke.ToDo;

import java.io.IOException;

/**
 * Encapsulates a ToDo command to create a ToDo task.
 */
public class ToDoCommand extends Command {

    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    private Storage storage;

    /**
     * Constructs a ToDoCommand object.
     *
     * @param list Task list.
     * @param des  Task description.
     */
    public ToDoCommand(TaskList list, String des) {
        super(list);
        super.task = new ToDo(des);
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
