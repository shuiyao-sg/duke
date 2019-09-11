package com.duke;

import java.util.Stack;

public class RecycleBin {
    private Stack<String> commandStrings;
    private Stack<Task> deletedTasks;

    public RecycleBin() {
        this.commandStrings = new Stack<>();
        this.deletedTasks = new Stack<>();
    }

    public String getCommandString() {
        return commandStrings.pop();
    }

    public Task getDeletedTask() {
        return deletedTasks.pop();
    }

    public void addCommandString(String command) {
        commandStrings.push(command);
    }

    public void addDeletedTasks(Task task) {
        deletedTasks.push(task);
    }
}
