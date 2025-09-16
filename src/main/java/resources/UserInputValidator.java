package resources;

import exceptions.IncorrectFormatException;
import exceptions.MissingArgumentException;

/**
 * All possible user inputs, and check if they are valid
 */
public class UserInputValidator {

    /**
     * for userinput that doesn't fall into any category
     */
    public static boolean isOthers(String userInput) {
        return !userInput.trim().equalsIgnoreCase("bye")
                && !userInput.trim().isEmpty();
    }
    /**
     * terminating condition if user says 'bye'
     * @param userInput for user input
     * @return boolean if so
     */
    public static boolean isBye(String userInput) {
        return userInput.equalsIgnoreCase("bye");
    }

    /**
     * 'list' for listing all tasks in LIST
     * @param userInput for user input
     */
    public static boolean isList(String userInput) {
        return userInput.equalsIgnoreCase("list");
    }

    /**
     * Check if the userInput matches load file format
     * @param userInput for user input
     * @return true if it's in the correct format
     */
    public static boolean isLoadFile(String userInput) {
        String[] parts = userInput.split("\\s+", 2);

        return parts[0].equalsIgnoreCase("load")
                && parts.length > 1
                && !parts[1].isEmpty();
    }

    /**
     * Checks if the userInput is in the format of "mark [task number]". Uses validTaskNumber().
     * @param userInput for user input
     * @return true if it is
     * @throws MissingArgumentException for missing argument
     */
    public static boolean isMark(String userInput) throws MissingArgumentException {
        //e.g. mark 2
        String[] words = userInput.split("\\s+");
        if (words[0].equalsIgnoreCase("mark")) {
            if (words.length == 2) {

                assert words[1] != null : "Task number string is null";
                assert Helper.isNumeric(words[1]) : "Task number is not numeric";

                return Helper.isNumeric(words[1]) && Helper.validTaskNumber(words[1]);
            } else {
                throw new MissingArgumentException("Missing task number. Format: mark [task number from 1 to n]");
            }
        }
        return false;
    }

    /**
     * Checks if the userInput is in the format of "unmark [task number]". Uses validTaskNumber().
     * @param userInput for user input
     * @return true if is unmark
     * @throws MissingArgumentException for missing arguments
     */
    public static boolean isUnmark(String userInput) throws MissingArgumentException {
        //e.g. unmark 2
        String[] words = userInput.split("\\s+");
        if (words[0].equalsIgnoreCase("unmark")) {
            if (words.length == 2) {

                assert words[1] != null : "Task number string is null";
                assert Helper.isNumeric(words[1]) : "Task number is not numeric";

                return Helper.isNumeric(words[1]) && Helper.validTaskNumber(words[1]);
            } else {
                throw new MissingArgumentException("Missing task number. Format: unmark [task number from 1 to n]");
            }
        }
        return false;
    }

    /**
     * Checks if the userInput is in the format of "delete [task number]". Uses validTaskNumberForDelete().
     * @param userInput for user input
     * @return true if it is for delete
     * @throws MissingArgumentException for missing argument
     */
    public static boolean isDelete(String userInput) throws MissingArgumentException {
        //e.g. delete 2
        String[] words = userInput.split(" ");
        if (words[0].equalsIgnoreCase("delete")) {
            if (words.length == 2) {

                assert words[1] != null : "Task number string is null";
                assert Helper.isNumeric(words[1]) : "Task number is not numeric";

                return Helper.isNumeric(words[1]) && Helper.validTaskNumberForDelete(words[1]);
            } else {
                throw new MissingArgumentException("Missing task number. Format: delete [task number from 1 to n]");
            }
        }
        return false;
    }

    /**
     * Check if userinput is in format reminder [days to due date]
     * @param userInput for userinput
     * @return true if it is in the correct format
     */
    public static boolean isReminder(String userInput) {
        String[] parts = userInput.split("\\s+", 2);
        boolean isReminder = parts[0].equalsIgnoreCase("reminder");
        boolean enoughParts = parts.length == 2;
        return isReminder && enoughParts && Helper.isNumeric(parts[1]);
    }

    /**
     * Checks that user input has format "due [date]"
     * @param userInput for user input
     * @return true if is checkdue
     */
    public static boolean isCheckDue(String userInput) {
        String[] parts = userInput.split("\\s+", 2);

        return parts[0].equalsIgnoreCase("due")
                && (parts.length == 2)
                && (!parts[1].trim().isEmpty());
    }

    /**
     * Checks if userInput is in the format "todo [task description]".
     * If not, it will throw exception with meaningful message.
     * @param userInput for user input
     * @return true if is todo
     * @throws MissingArgumentException for missing argument
     */
    public static boolean isTodo(String userInput) throws MissingArgumentException {
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

    /**
     * Checks if userInput is in the format "deadline [task description] [by date]".
     * If not, it will throw exception with meaningful message.
     * @param userInput for user input
     * @return true if is deadline
     * @throws MissingArgumentException for missing argument
     */
    public static boolean isDeadline(String userInput) throws MissingArgumentException {
        String[] parts = userInput.split("\\s+", 2);

        if (parts[0].equalsIgnoreCase("deadline")) {
            if (parts.length <= 1 || parts[1].trim().isEmpty() || parts[1].trim().startsWith("/")) {
                throw new MissingArgumentException("Missing task description."
                        + " Format: deadline [task description] /by [deadline]");
            }
            String remaining = parts[1].trim();
            if (!remaining.contains("/by")) {
                throw new IncorrectFormatException("Please add '/by'. "
                        + "Format: deadline [task description] /by [deadline]");
            }
            String[] byParts = remaining.split("/by", 2);
            if (byParts.length < 2) {
                throw new MissingArgumentException("Missing 'by' date. "
                        + "Format: deadline [task description] /by [deadline]");
            }
            String byDate = byParts[1].trim();
            if (byDate.isEmpty()) {
                throw new MissingArgumentException("Missing 'by' date. "
                        + "Format: deadline [task description] /by [deadline]");
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if userInput is in the format "event [task description] [from date] [by date]".
     * If not, it will throw exception with meaningful message.
     * @param userInput for user input
     * @return true if is event
     * @throws MissingArgumentException for missing argument
     * @throws IncorrectFormatException for incorrect format
     */
    public static boolean isEvent(String userInput) throws MissingArgumentException, IncorrectFormatException {
        String[] parts = userInput.split("\\s+", 2);

        if (parts[0].equalsIgnoreCase("event")) {
            if (parts.length <= 1 || parts[1].trim().isEmpty() || parts[1].trim().startsWith("/")) {
                throw new MissingArgumentException("Missing task description. "
                        + "Format: event [task description] /from [date] /to [date]");
            }
            String remaining = parts[1].trim();
            if (!remaining.contains("/from")) {
                throw new IncorrectFormatException("Please add '/from'. "
                        + "Format: event [task description] /from [date] /to [date]");
            }
            String[] fromParts = remaining.split("/from", 2);
            if (fromParts.length < 2 || fromParts[1].trim().startsWith("/to")) {
                throw new MissingArgumentException("Missing '/from' date. "
                        + "Format: event [task description] /from [date] /to [date]");
            }
            String fromDate = fromParts[1].trim();
            if (fromDate.isEmpty()) {
                throw new MissingArgumentException("Missing 'from' date. "
                        + "Format: event [task description] /from [date] /to [date]");
            }
            if (!remaining.contains("/to")) {
                throw new IncorrectFormatException("Please add '/to'. "
                        + "Format: event [task description] /from [date] /to [date]");
            }
            String[] toParts = remaining.split("/to", 2);
            if (toParts.length < 2) {
                throw new MissingArgumentException("Missing 'to' date. "
                        + "Format: event [task description] /from [date] /to [date]");
            }
            String toDate = toParts[1].trim();
            if (toDate.isEmpty()) {
                throw new MissingArgumentException("Missing 'to' date. "
                        + "Format: event [task description] /from [date] /to [date]");
            }
            return true;
        }
        return false;
    }

    /**
     * Check if the format of user input matches find
     * @param userInput for user input
     * @return true if correct format
     */
    public static boolean isFind(String userInput) {
        String[] parts = userInput.split("\\s+", 2);

        return parts[0].equalsIgnoreCase("find")
                && (parts.length == 2)
                && (!parts[1].trim().isEmpty());
    }


}
