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
    public static final List<Task> LIST = new ArrayList<Task>();

    public static final String HELLO = "Bello! Me is" + CHATBOT_NAME;
    public static final String BYE = "Bye bye! Me eat banana ~";
    public static final String CONFUSED = "Bello bello??";
    public static final String MARKASDONE = "Woohoo banananana done:\n\t";
    public static final String MARKNOTDONE = "Stupa, faster finish it missy:\n\t";
    public static final String REMOVETASK = "Throw into trash! bi-do:\n\t";
    public static final String ADDTASK = "Wahh more work added ~ tulaliloo:\n\t";
    public static final String LOADED = "All in!";
    public static final String DUEONTHISDAY = "Woohoo tasks due on ";
    public static final String FINDRESULTS = "Sneak peek ~";
    public static final String REMINDERS = "Attention! ";
    public static final String NOREMINDERS = "Woohoo no tasks due ~";
    public static final String INITIALISE = HELLO + "\n\n"
            + "Want more work?\n"
            + "  - todo [description]\n"
            + "  - deadline [description] /by [date]\n"
            + "  - event [description] /from [date] /to [date]\n\n"

            + "Manage tasks:\n"
            + "  - list\n"
            + "  - find [keyword]\n"
            + "  - mark/unmark/delete [number]\n"
            + "  - due [date]\n"
            + "  - reminder [days]\n"
            + "  - load [filename]\n\n"

            + "Type 'bye' to exit (bye bye ~)";
}
