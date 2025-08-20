package Tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        String formatted = String.format(" (by: %s)", this.by);
        return "[D]" + super.toString() + formatted;
    }


}
