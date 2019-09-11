package com.duke.command;

import com.duke.Task;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Represents command to be executed by Duke.
 */
public abstract class Command {
    protected static final String FILE_PATH = "src/data/duke.txt";

    protected TaskList list;
    protected Task task;

    /**
     * Constructs a Command object.
     *
     * @param taskList input task list.
     */
    public Command(TaskList taskList) {
        this.list = taskList;
    }

    public Command() {
    }

    public abstract String execute() throws IOException;

    String stringifyTask(TaskList list, Task task, String firstLine) {
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";
        return firstLine + "\n" + task.toString() + "\n" + lastLine;
    }

    /**
     * Checks whether the Command is a ByeCommand.
     *
     * @return true if it is a ByeCommand, false otherwise.
     */
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Checks whether the Command is a DeleteCommand.
     *
     * @return true if it is a DeleteCommand, false otherwise.
     */
    public boolean isDeleteCommand() {
        return false;
    }

    /**
     * Checks whether the Command is a UndoCommand.
     *
     * @return true if it is a UndoCommand, false otherwise.
     */
    public boolean isUndoCommand() {
        return false;
    }

    public Task getDeletedTask() {
        return new Task("");
    }
}
