package tasks;

import static resources.DateHandler.isDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import resources.Constants;
import resources.FileHandler;
import resources.Helper;

/**
 * Event class that extends Task
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event class
     * @param description for task description
     * @param from when event starts
     * @param to when event ends
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * print out the event task
     * @return String with description, from, to with formatter
     */
    public String toString() {
        if (this.to == null) {
            String formatted = String.format(" (on: %s)",
                    this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            return "[E]" + super.toString() + formatted;
        }
        String formatted = String.format(" (from: %s to: %s)",
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return "[E]" + super.toString() + formatted;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    /**
     * respond to user input of adding an event task to LIST
     * @param userInput for user input
     * @return chatbot's respond to adding an event task to LIST
     */
    public static String respondTo(String userInput) {
        String[] fromSplit = userInput.split("/from", 2);
        String description = fromSplit[0].replaceFirst("event", "").trim();

        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();
        String to = toSplit[1].trim();

        LocalDate fromDate = isDate(from);
        LocalDate toDate = isDate(to);
        Event newEvent = new Event(description, fromDate, toDate);
        Constants.LIST.add(newEvent);
        FileHandler.save();
        return (Constants.ADDTASK
                + newEvent + "\n"
                + Helper.tasksLeft(Constants.LIST.size()));
    }

    /**
     * Write the task to the file when application is closed
     * @return String to be written into file
     */
    public String writeToFile() {
        return "E" + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description + " | " + this.from + " | "
                + this.to;
    }
}
