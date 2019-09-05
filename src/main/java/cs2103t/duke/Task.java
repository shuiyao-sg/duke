package cs2103t.duke;

import cs2103t.duke.exceptions.DukeIllegalArgumentException;
import cs2103t.duke.exceptions.RedundantOperationException;

/**
 * Encapsulates tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a cs2103t.duke.Task object.
     *
     * @param description task description.
     * @throws DukeIllegalArgumentException if encountered invalid user input.
     */
    public Task(String description) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("Please specify your task description after your command");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[" + "Y" + "]" : "[" + "N" + "]"); //return Y or N symbols
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        if (this.isDone) {
            throw new RedundantOperationException("The task has already been done");
        }
        this.isDone = true;
    }

    /**
     * Converts String in file to cs2103t.duke.Task.
     *
     * @param s String representation of tasks in file.
     * @return cs2103t.duke.Task specified by the String.
     */
    public static Task genTaskFromFileString(String s) {
        char taskType = s.charAt(1);
        String isDone = s.charAt(4) + "";

        int index = s.indexOf("] ");
        String taskContent = s.substring(index + 1).trim();

        switch (taskType) {
        case 'T':
            Task todo = new ToDo(taskContent);
            if (isDone.equals("\u2713")) {
                todo.markAsDone();
            }
            return todo;
        case 'D':
            String[] deadlineContentArray = taskContent.split("by:");
            Task deadline = new Deadline(deadlineContentArray[0].trim(), deadlineContentArray[1].trim());
            if (isDone.equals("\u2713")) {
                deadline.markAsDone();
            }
            return deadline;
        case 'E':
            String[] eventContentArray = taskContent.split("at:");
            Task event = new Event(eventContentArray[0].trim(), eventContentArray[1].trim());
            if (isDone.equals("\u2713")) {
                event.markAsDone();
            }
            return event;
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
