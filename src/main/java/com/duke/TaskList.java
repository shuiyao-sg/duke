package com.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Encapsulates a list of the tasks.
 */
public class TaskList {
    private static final int INDENT_LEVEL = 5;

    private List<Task> listBody;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.listBody = new ArrayList<>();
    }

    private TaskList(List<Task> tasks) {
        this.listBody = tasks;
    }

    /**
     * Adds task to the TaskList.
     *
     * @param task task to be added to task list.
     */
    public void addTask(Task task) {
        this.listBody.add(task);
    }

    public void addTask(int index, Task task) {
        this.listBody.add(index, task);
    }

    /**
     * Deletes task from the TaskList.
     *
     * @param index index from user input.
     */
    public void deleteTask(int index) {
        this.listBody.remove(index);
    }

    /**
     * Retrieves task from the TaskList.
     *
     * @param index index from user input.
     * @return task to find.
     */
    public Task getTask(int index) {
        return this.listBody.get(index);
    }

    /**
     * Returns size of the task list.
     *
     * @return size of the task list.
     */
    public int size() {
        return this.listBody.size();
    }

    /**
     * Generates a task list with each task containing specified text.
     *
     * @param text text to search.
     * @return task list with tasks containing input text.
     */
    public TaskList query(String text) {
        List<Task> tasks = this.listBody.stream()
                .filter(task -> task.toString().contains(text))
                .collect(Collectors.toList());
        return new TaskList(tasks);
    }

    @Override
    public String toString() {
        return IntStream.range(0, this.listBody.size())
                .mapToObj(i -> (i + 1) + "." + listBody.get(i).toString() + "\n")
                .reduce("", (x, y) -> x + y);
    }
}
