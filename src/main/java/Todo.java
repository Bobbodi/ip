public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }


}
