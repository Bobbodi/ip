package Resources;

import Exceptions.*;

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

    public static boolean validTaskNumber(String str) throws InvalidTaskNumberException {
        if (Integer.parseInt(str) <= Constants.LIST.size() &&
                Integer.parseInt(str) > 0) {
            return true;
        } else {
            throw new InvalidTaskNumberException(String.format("%s is not a valid task number.", str));
        }
    }

    public static boolean isMark(String userInput) throws MissingArgumentException {
        //e.g. mark 2
        String[] words = userInput.split(" ");
        if (words[0].equalsIgnoreCase("mark")) {
            if (words.length == 2) {
                return isNumeric(words[1]) &&
                        validTaskNumber(words[1]);
            } else {
                throw new MissingArgumentException("Missing task number. Format: mark [task number from 1 to n]");
            }
        }
        return false;
    }

    public static boolean isUnmark(String userInput) throws MissingArgumentException{
        //e.g. unmark 2
        String[] words = userInput.split(" ");
        if (words[0].equalsIgnoreCase("unmark")) {
            if (words.length == 2) {
                return isNumeric(words[1]) &&
                        validTaskNumber(words[1]);
            } else {
                throw new MissingArgumentException("Missing task number. Format: unmark [task number from 1 to n]");
            }
        }
        return false;
    }

    public static boolean isTodo(String userInput) throws MissingArgumentException{
        //e.g. to.do borrow book
        String[] words = userInput.split(" ");
        if (words[0].equalsIgnoreCase("todo")) {
            if (words.length > 1) {
                return true;
            } else {
                throw new MissingArgumentException("Missing task description. Format: todo [task description]");
            }
        }
        return false;
    }

    public static boolean isDeadline(String userInput) throws MissingArgumentException {
        String[] parts = userInput.split("\\s+", 2);

        if (parts[0].equalsIgnoreCase("deadline")) {
            if (parts.length <= 1 || parts[1].trim().isEmpty() || parts[1].trim().startsWith("/")) {
                throw new MissingArgumentException("Missing task description. Format: deadline [task description] /by [deadline]");
            }
            String remaining = parts[1].trim();
            if (!remaining.contains("/by")) {
                throw new IncorrectFormatException("Please add '/by'. Format: deadline [task description] /by [deadline]");
            }
            String[] byParts = remaining.split("/by", 2);
            if (byParts.length < 2) {
                throw new MissingArgumentException("Missing 'by' date. Format: deadline [task description] /by [deadline]");
            }
            String byDate = byParts[1].trim();
            if (byDate.isEmpty()) {
                throw new MissingArgumentException("Missing 'by' date. Format: deadline [task description] /by [deadline]");
            }
            return true;
        }
        return false;
    }

    public static boolean isEvent(String userInput) throws MissingArgumentException, IncorrectFormatException {
        String[] parts = userInput.split("\\s+", 2);

        if (parts[0].equalsIgnoreCase("event")) {
            if (parts.length <= 1 || parts[1].trim().isEmpty() || parts[1].trim().startsWith("/")) {
                throw new MissingArgumentException("Missing task description. Format: event [task description] /from [date] /to [date]");
            }
            String remaining = parts[1].trim();
            if (!remaining.contains("/from")) {
                throw new IncorrectFormatException("Please add '/from'. Format: event [task description] /from [date] /to [date]");
            }
            String[] fromParts = remaining.split("/from", 2);
            if (fromParts.length < 2 || fromParts[1].trim().startsWith("/to")) {
                throw new MissingArgumentException("Missing '/from' date. Format: event [task description] /from [date] /to [date]");
            }
            String fromDate = fromParts[1].trim();
            if (fromDate.isEmpty()) {
                throw new MissingArgumentException("Missing 'from' date. Format: event [task description] /from [date] /to [date]");
            }
            if (!remaining.contains("/to")) {
                throw new IncorrectFormatException("Please add '/to'. Format: event [task description] /from [date] /to [date]");
            }
            String[] toParts = remaining.split("/to", 2);
            if (toParts.length < 2) {
                throw new MissingArgumentException("Missing 'to' date. Format: event [task description] /from [date] /to [date]");
            }
            String toDate = toParts[1].trim();
            if (toDate.isEmpty()) {
                throw new MissingArgumentException("Missing 'to' date. Format: event [task description] /from [date] /to [date]");
            }
            return true;
        }
        return false;
    }

    public static String tasksLeft(int num) {
        String taskGrammer = "tasks";
        if (num == 1) {
            taskGrammer = "task";
        }
        return "Now you have " + num + " " + taskGrammer + " in the list.";
    }

}