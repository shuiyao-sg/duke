import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int INDENT_LEVEL = 5;

    private List<Task> listBody;

    public TaskList() {
        this.listBody = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.listBody.add(task);
    }

    public void deleteTask(int index) {
        this.listBody.remove(index);
    }

    public Task getTask(int index) {
        return this.listBody.get(index);
    }

    public int size() {
        return this.listBody.size();
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < listBody.size(); i++) {
            int index = i + 1;
            Task task = listBody.get(i);
            String temp = index + "." + task.toString();
            output += String.format("%1$" + (temp.length() + INDENT_LEVEL) + "s\n", temp);
        }
        return output;
    }


}
