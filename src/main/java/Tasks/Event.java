public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        String formatted = String.format(" (from: %s to: %s)", this.from, this.to);
        return "[E]" + super.toString() + formatted;
    }
}
