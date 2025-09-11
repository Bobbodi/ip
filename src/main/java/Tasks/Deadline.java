package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


}
