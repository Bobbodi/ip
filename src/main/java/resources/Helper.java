package resources;

import java.text.NumberFormat;
import java.text.ParsePosition;

import exceptions.EmptyListException;
import exceptions.InvalidTaskNumberException;

/**
 * Include all helper functions that are used in Bobbodi class
 */
public class Helper {
    /**
     * Format tasks in LIST nicely.
     * @return String of tasks.toString()
     */
    public static String formatList() {
        if (Constants.LIST.isEmpty()) {
            return "There are no tasks to display!";
        }

        StringBuilder text = new StringBuilder();
        text.append("Here are the tasks in your list:\n");
        for (int i = 0; i < Constants.LIST.size(); i++) {
            String numbering = String.format("\t%2d. ", i + 1);
            text.append(numbering).append(Constants.LIST.get(i));
            if (i < Constants.LIST.size() - 1) {
                text.append("\n");
            }
        }
        return text.toString();
    }

    /**
     * Checks if text is a numeric number
     * @param text String
     * @return true if it is; false otherwise
     */
    public static boolean isNumeric(String text) {
        if (text.isEmpty()) {
            return false;
        }
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(text, pos);
        return text.length() == pos.getIndex();
    }

    /**
     * Checks validity of task number entered for 'mark', 'unmark', 'delete'.
     * Task number must be more than or equal to 1 and less than the total number of tasks in LIST.
     * @param str is a task number
     * @return true if it is;
     * @throws InvalidTaskNumberException for invalid task
     */
    public static boolean validTaskNumber(String str) throws InvalidTaskNumberException {
        boolean withinListLength = Integer.parseInt(str) <= Constants.LIST.size();
        boolean moreThanZero = Integer.parseInt(str) > 0;
        if (withinListLength && moreThanZero) {
            return true;
        } else {
            throw new InvalidTaskNumberException(String.format("%s is not a valid task number.", str));
        }
    }

    /**
     * Checks if the task number is valid for deletion. There must be tasks in the LIST and the number must be
     * less than or equal to the length of the LIST.
     * @param str for task number
     * @return true if is valid to delete the task from LIST
     * @throws InvalidTaskNumberException for invalid task number
     */
    public static boolean validTaskNumberForDelete(String str) throws InvalidTaskNumberException {
        if (Constants.LIST.isEmpty()) {
            throw new EmptyListException("There are no tasks to delete!");
        }
        boolean withinListLength = Integer.parseInt(str) <= Constants.LIST.size();
        boolean moreThanZero = Integer.parseInt(str) > 0;
        if (withinListLength && moreThanZero) {
            return true;
        } else {
            throw new InvalidTaskNumberException(String.format("%s is not a valid task number.", str));
        }
    }

    /**
     * For printing purposes. If number of tasks left is 1, it prints "task", else, "tasks"
     * @param num for number of tasks left
     * @return task in formatted string
     */
    public static String tasksLeft(int num) {
        String taskGrammer = "tasks";
        if (num == 1) {
            taskGrammer = "task";
        }
        return "Now you have " + num + " " + taskGrammer + " in the list.";
    }

}
