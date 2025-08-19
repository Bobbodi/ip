public class Event extends Task {

    protected String by;
    protected String from;

    public Event(String description, String by, String from) {
        super(description);
        this.by = by;
        this.from = from;
    }

    public String toString() {
        String formatted = String.format(" (from: %s to: %s)", this.from, this.by);
        return "[E]" + super.toString() + formatted;
    }
}
