import java.text.NumberFormat;
import java.text.ParsePosition;

public class Helper {
    public static String formatLIST() {
        StringBuilder text = new StringBuilder();
        text.append("Here are the tasks in your list:\n");
        for (int i = 0; i < Constants.LIST.size(); i++) {
            String numbering = String.format("\t%2d. ", i+1);
            text.append(numbering).append(Constants.LIST.get(i));
            if (i < Constants.LIST.size() - 1) {
                text.append("\n");
            }
        }
        return text.toString();
    }

    public static void chatbotSays(String text) {
        System.out.println(Constants.SEPARATOR);
        System.out.println(Constants.PURPLE + text + Constants.RESET);
        System.out.println(Constants.SEPARATOR);
    }

    public static boolean isNumeric(String text) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(text, pos);
        return text.length() == pos.getIndex();
    }

    public static boolean validTaskNumber(String str) {
        return Integer.parseInt(str) <= Constants.LIST.size() &&
                Integer.parseInt(str) > 0;
    }
    public static boolean isMark(String userInput) {
        //e.g. mark 2
        String[] words = userInput.split(" ");
        return words[0].equalsIgnoreCase("mark") &&
                words.length == 2 &&
                isNumeric(words[1]) &&
                validTaskNumber(words[1]);
    }

    public static boolean isUnmark(String userInput) {
        //e.g. unmark 2
        String[] words = userInput.split(" ");
        return words[0].equalsIgnoreCase("unmark") &&
                words.length == 2 &&
                isNumeric(words[1]) &&
                validTaskNumber(words[1]);
    }

    public static boolean isTodo(String userInput) {
        //e.g. to.do borrow book
        String[] words = userInput.split(" ");
        return words[0].equalsIgnoreCase("todo");
    }

    public static boolean isDeadline(String userInput) {
        String[] words = userInput.split("/");
        String[] taskDetails = words[0].split(" ");
        String[] by = null;
        if (words.length > 1) {
            by = words[1].split(" ");
        }
        return taskDetails[0].equalsIgnoreCase("deadline") &&
                by != null &&
                by[0].equalsIgnoreCase("by");
    }

    public static boolean isEvent(String userInput) {
        String[] words = userInput.split("/");
        String[] taskDetails = words[0].split(" ");
        String[] from = null;
        String[] to = null;
        if (words.length > 2) {
            from = words[1].split(" ");
            to = words[2].split(" ");
        }
        return taskDetails[0].equalsIgnoreCase("event") &&
                from != null &&
                from[0].equalsIgnoreCase("from") &&
                to[0].equalsIgnoreCase("to");
    }

    public static String tasksLeft(int num) {
        String taskGrammer = "tasks";
        if (num == 1) {
            taskGrammer = "task";
        }
        return "Now you have " + num + " " + taskGrammer + " in the list.";
    }

}
