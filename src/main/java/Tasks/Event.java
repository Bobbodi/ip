package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}
