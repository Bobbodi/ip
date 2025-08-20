package Resources;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String DASHES = "---------------------------------------------";
    public static final String CHATBOT_NAME = "Bobbodi";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String SEPARATOR = BLUE + DASHES + RESET;
    public static final List<Task> LIST = new ArrayList<Task>();

    public static final String HELLO = "Bonjour sistar!" + CHATBOT_NAME + "in the house ~\n";
    public static final String BYE = "Come back, be here!";
    public static final String MARKASDONE = "Woohoo it's done:\n\t";
    public static final String MARKNOTDONE = "Faster finish it madam:\n\t";
    public static final String REMOVETASK = "New trash alert:\n\t";
    public static final String ADDTASK = "Wahh more work added ~:\n\t";
    public static final String ADDED = "Inserted: ";
}
