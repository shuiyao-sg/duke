package cs2103t.duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a list of the tasks.
 */
public class TaskList {
    private static final int INDENT_LEVEL = 5;

    private List<Task> listBody;

    /**
     * Constructs a cs2103t.duke.TaskList object.
     */
    public TaskList() {
        this.listBody = new ArrayList<>();
    }

    /**
     * Adds task to the cs2103t.duke.TaskList.
     *
     * @param task task to be added to task list.
     */
    public void addTask(Task task) {
        this.listBody.add(task);
    }

    /**
     * Deletes task from the cs2103t.duke.TaskList.
     *
     * @param index index from user input.
     */
    public void deleteTask(int index) {
        this.listBody.remove(index);
    }

    /**
     * Retrieves task from the cs2103t.duke.TaskList.
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

    public TaskList query(String text) {
        TaskList taskList = new TaskList();
        for (Task task : this.listBody) {
            if (task.toString().contains(text)) {
                taskList.addTask(task);
            }
        }
        return taskList;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < listBody.size(); i++) {
            int index = i + 1;
            Task task = listBody.get(i);
            String temp = index + "." + task.toString();
            output += temp + "\n";
        }
        return output;
    }
}
