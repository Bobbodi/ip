package resources;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * Declare constants that are referenced in Bobbodi class
 */
public class Constants {

    public static final String DASHES = "---------------------------------------------";
    public static final String CHATBOT_NAME = "Bobbodi";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String SEPARATOR = BLUE + DASHES + RESET;
    public static final List<Task> LIST = new ArrayList<Task>();

    public static final String HELLO = "Aloha! It's " + CHATBOT_NAME;
    public static final String BYE = "Bye bye!";
    public static final String MARKASDONE = "Woohoo it's done:\n\t";
    public static final String MARKNOTDONE = "Faster finish it madam:\n\t";
    public static final String REMOVETASK = "New trash alert:\n\t";
    public static final String ADDTASK = "Wahh more work added ~:\n\t";
    public static final String ADDED = "Inserted: ";
    public static final String LOADED = "All in!";
    public static final String DUEONTHISDAY = "Woohoo here are the tasks due on ";
    public static final String FINDRESULTS = "Sneak peak is here ~";
    public static final String REMINDERS = "Attention! These tasks are due soon";
    public static final String INITIALISE = HELLO + "\n\n"
            + "More work?\n"
            + "  - todo [description]\n"
            + "  - deadline [description] /by [date]\n"
            + "  - event [description] /from [date] /to [date]\n\n"

            + "Manage tasks:\n"
            + "  - list, find [keyword]\n"
            + "  - mark/unmark [number]\n"
            + "  - delete [number]\n"
            + "  - check due, reminder [days]\n"
            + "  - load file [filename]\n\n"

            + "Type 'bye' to exit";
}
