package tasks;

import resources.Constants;
import resources.FileHandler;
import resources.Helper;

/**
 * Todo class with additional [T] for toString
 */
public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Used in getResponse() method of Bobbodi class where it adds a todo task to the LIST
     * @param userInput for userinput
     * @return chatbot response to adding a todo task
     */
    public static String respondTo(String userInput) {
        String description = userInput.replaceFirst("todo", "").trim();
        Todo newTodo = new Todo(description);
        Constants.LIST.add(newTodo);
        FileHandler.save();
        return (Constants.ADDTASK
                + newTodo + "\n" + Helper.tasksLeft(Constants.LIST.size()));
    }

    public String writeToFile() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + this.description;
    }
}
