import cs2103t.duke.exceptions.DukeFileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void appendText(String input) throws DukeFileNotFoundException, IOException {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(input + System.lineSeparator());
            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    public void overwriteText(String oldString, String newString) throws IOException {
        try {
            List<String> inputList = Files.readAllLines(Paths.get(filePath));

            String output = "";

            for (String s : inputList) {
                if (s.equals(oldString)) {
                    output += newString + System.lineSeparator();
                } else {
                    output += s + System.lineSeparator();
                }
            }

            FileWriter fw = new FileWriter(filePath, false);
            fw.write(output);
            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    public void deleteText(String toDelete) throws IOException {
        try {
            List<String> inputList = Files.readAllLines(Paths.get(filePath));
            String output = "";

            for (String s : inputList) {
                if (!s.equals(toDelete)) {
                    output += s + System.lineSeparator();
                }
            }

            FileWriter fw = new FileWriter(filePath, false);
            fw.write(output);
            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    public TaskList genTaskListFromFile() {
        TaskList taskList = new TaskList();
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            while (fileScanner.hasNextLine()) {
                String nextLineOfFile = fileScanner.nextLine().trim();
                Task task = Task.genTaskFromFileString(nextLineOfFile);
                taskList.addTask(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.err.println("File " + filePath + " not found by Duke. New task list generated");
            return new TaskList();
        }
    }

    public void printFile() {
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            String heading = "File successfully loaded. Remaining tasks: ";
            System.out.printf("%1$" + (heading.length() + 5) + "s\n", heading);

            while (fileScanner.hasNextLine()) {
                String nextLineOfFile = fileScanner.nextLine().trim();
                System.out.printf("%1$" + (nextLineOfFile.length() + 5) + "s\n", nextLineOfFile);
            }
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("FIle " + filePath + " not found by Duke");
        }
    }
}
