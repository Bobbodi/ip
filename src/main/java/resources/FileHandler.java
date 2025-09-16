package resources;

import static resources.DateHandler.isDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import exceptions.IncorrectFormatException;
import exceptions.InvalidTaskNumberException;
import exceptions.MissingArgumentException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Abstract out methods associated with loading and reading the file
 */
public class FileHandler {

    /**
     * Reads and loads tasks from file. The filepath is relative to the base location.
     * @param userInput to determine file path relative
     */
    public static String loadfile(String userInput) {
        String[] parts0 = userInput.split("\\s+", 2);
        String filePath = parts0[1].trim();
        try {
            String output = String.format("Reading file %s...", filePath);
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" - ");
                validateFileLine(parts);
                switch (parts[0]) {
                case "T":
                    Todo todo = new Todo(parts[2]);
                    todo.setDone(parts[1]);
                    Constants.LIST.add(todo);
                    break;
                case "D":
                    validateFileLineDeadline(parts);
                    LocalDate byDate = isDate(parts[3]);
                    Deadline deadline = new Deadline(parts[2], byDate);
                    deadline.setDone(parts[1]);
                    Constants.LIST.add(deadline);
                    break;
                case "E":
                    validateFileLineEvent(parts);
                    LocalDate fromDate = isDate(parts[3]);
                    LocalDate byDate1 = isDate(parts[4]);
                    Event event = new Event(parts[2], fromDate, byDate1);
                    event.setDone(parts[1]);
                    Constants.LIST.add(event);
                    break;
                default:
                    throw new IncorrectFormatException(String.format("Unknown task type %s", parts[0]));
                }
            }
            output += (Constants.LOADED);
            return output;
        } catch (FileNotFoundException e) {
            return (String.format("File %s not found", filePath));
        } catch (IncorrectFormatException | InvalidTaskNumberException | MissingArgumentException e) {
            return (e.getMessage());
        }
    }

    /**
     * Checks that each line of file is readable. [Task type] | [Completion status] | [Task description]
     * @param parts for parts of the file line
     */
    public static void validateFileLine(String[] parts) {
        if (parts.length < 3) {
            throw new IncorrectFormatException("Line is invalid. Please use: "
                    + "[Task type] | [Completion status] | [Task description]");
        }
        if (!parts[1].equalsIgnoreCase("1") && !parts[1].equalsIgnoreCase("0")) {
            throw new InvalidTaskNumberException(String.format("Cannot read %s. "
                    + "Use 1 or 0 for task completion", parts[1]));
        }
        if (parts[2].isEmpty()) {
            throw new IncorrectFormatException("Task description cannot be empty");
        }
    }

    /**
     * Checks that file line is a readable deadline with format
     * [Task type] | [Completion status] | [Task description] | [by date]
     * @param parts for parts of the file line for deadline
     */
    public static void validateFileLineDeadline(String[] parts) {
        if (parts.length < 4) {
            throw new IncorrectFormatException("Line is invalid for task type 'Deadline'. Please use: "
                    + "[Task type] | [Completion status] | [Task description] | [by date]");
        }
    }

    /**
     * Checks that file line is a readable event with format
     * [Task type] | [Completion status] | [Task description] | [from date] | [by date]
     * @param parts ofr parts of the file line for event
     */
    public static void validateFileLineEvent(String[] parts) {
        if (parts.length < 5) {
            throw new IncorrectFormatException("Line is invalid for task type 'Event'. Please use: "
                    + "[Task type] | [Completion status] | [Task description] | [from date] | [by date]");
        }
    }

}
