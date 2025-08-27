package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        String formatted = String.format(" (by: %s)",
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return "[D]" + super.toString() + formatted;
    }

    public LocalDate getBy() {
        return this.by;
    }


}
