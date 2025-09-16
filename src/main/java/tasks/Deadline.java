package tasks;

import static resources.DateHandler.isDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import resources.Constants;
import resources.Helper;

/**
 * Deadline class with additional field 'by'
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for deadline
     * @param description of task
     * @param by due date
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * toString for deadline task
     * @return String containing description and by date
     */
    public String toString() {
        String formatted = String.format(" (by: %s)",
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return "[D]" + super.toString() + formatted;
    }

    public LocalDate getBy() {
        return this.by;
    }

    /**
     * respond to user input of adding a deadline task to LIST
     * @param userInput for user input
     * @return chatbot's response to succesfully adding task
     */
    public static String respondTo(String userInput) {
        String[] parts = userInput.split("/by", 2);
        String description = parts[0].replaceFirst("deadline", "").trim();
        String by = parts[1].trim();

        LocalDate byDate = isDate(by);
        Deadline newDeadline = new Deadline(description, byDate);
        Constants.LIST.add(newDeadline);

        return (Constants.ADDTASK
                + newDeadline + "\n"
                + Helper.tasksLeft(Constants.LIST.size()));
    }

}
