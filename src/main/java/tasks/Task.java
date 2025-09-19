package tasks;

/**
 * Task base class with description, isDone
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task with description
     * @param description for task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * If 0, set to not done. If 1, set to done
     * @param s 0 or 1 in string
     */
    public void setDone(String s) {
        if (s.equalsIgnoreCase("0")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Write the task to the file when application is closed
     * @return String to be written into file
     */
    public String writeToFile() {
        return "";
    }
}
