package com.duke;

import com.duke.exceptions.DukeFileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates a file I/O class to deal with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends text to the back of a file.
     *
     * @param input text to append.
     * @throws DukeFileNotFoundException if file not found.
     * @throws IOException               if failed to read or write to file.
     */
    public void appendText(String input) throws DukeFileNotFoundException, IOException {
        try {
            writeToFile(input + System.lineSeparator(), true);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    /**
     * Replaces chosen text in the file with a new String.
     *
     * @param oldString text to be overwritten.
     * @param newString new text.
     * @throws IOException if failed to read or write to file.
     */
    public void overwriteText(String oldString, String newString) throws IOException {
        try {
            String output = validateTextOutput(oldString, newString, true);
            writeToFile(output, false);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    /**
     * Deletes the chosen text from the file.
     *
     * @param toDelete text to delete.
     * @throws IOException if failed to read or write to file.
     */
    public void deleteText(String toDelete) throws IOException {
        try {
            String output = validateTextOutput(toDelete, "", false);
            writeToFile(output, false);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    private void writeToFile(String input, boolean isAppendable) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppendable);
        fw.write(input);
        fw.close();
    }

    private String validateTextOutput(String oldString, String newString, boolean isOverwrite) throws IOException {
        List<String> inputList = Files.readAllLines(Paths.get(filePath));

        String output = "";
        for (String s : inputList) {
            if (!s.equals(oldString)) {
                output += s + System.lineSeparator();
            } else if (isOverwrite) {
                output += newString + System.lineSeparator();
            }
        }
        return output;
    }

    /**
     * Generates a TaskList from file input.
     *
     * @return TaskList with tasks remaining in the file.
     */
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
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("File " + filePath + " not found by Duke. New task list generated");
            return new TaskList();
        }
    }

    /**
     * Prints the file input.
     */
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
            return;
        }
    }
}
