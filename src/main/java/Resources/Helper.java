package Resources;

import Exceptions.*;
import Tasks.*;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Helper {
    /**
     * Format tasks in LIST nicely.
     * @return String of tasks.toString()
     */
    public static String formatLIST() {
        if (Constants.LIST.isEmpty()) {
            return "There are no tasks to display!";
        }

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

    /**
     * Abstracts out chatbot text design
     * @param text
     */
    public static void chatbotSays(String text) {
        System.out.println(Constants.SEPARATOR);
        System.out.println(Constants.PURPLE + text + Constants.RESET);
        System.out.println(Constants.SEPARATOR);
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
     * @param str
     * @return true if it is;
     * @throws InvalidTaskNumberException
     */
    public static boolean validTaskNumber(String str) throws InvalidTaskNumberException {
        if (Integer.parseInt(str) <= Constants.LIST.size() &&
                Integer.parseInt(str) > 0) {
            return true;
        } else {
            throw new InvalidTaskNumberException(String.format("%s is not a valid task number.", str));
        }
    }

    /**
     * Checks if the userInput is in the format of "mark [task number]". Uses validTaskNumber().
     * @param userInput
     * @return true if it is
     * @throws MissingArgumentException
     */
    public static boolean isMark(String userInput) throws MissingArgumentException {
        //e.g. mark 2
        String[] words = userInput.split("\\s+");
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

    /**
     * Checks if the userInput is in the format of "unmark [task number]". Uses validTaskNumber().
     * @param userInput
     * @return
     * @throws MissingArgumentException
     */
    public static boolean isUnmark(String userInput) throws MissingArgumentException{
        //e.g. unmark 2
        String[] words = userInput.split("\\s+");
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

    /**
     * Checks if the task number is valid for deletion. There must be tasks in the LIST and the number must be
     * less than or equal to the length of the LIST.
     * @param str
     * @return
     * @throws InvalidTaskNumberException
     */
    public static boolean validTaskNumberForDelete(String str) throws InvalidTaskNumberException {
        if (Constants.LIST.isEmpty()) {
            throw new EmptyListException("There are no tasks to delete!");
        } else if (Integer.parseInt(str) <= Constants.LIST.size() &&
                Integer.parseInt(str) > 0) {
            return true;
        } else {
            throw new InvalidTaskNumberException(String.format("%s is not a valid task number.", str));
        }
    }

    /**
     * Checks if the userInput is in the format of "delete [task number]". Uses validTaskNumberForDelete().
     * @param userInput
     * @return
     * @throws MissingArgumentException
     */
    public static boolean isDelete(String userInput) throws MissingArgumentException{
        //e.g. delete 2
        String[] words = userInput.split(" ");
        if (words[0].equalsIgnoreCase("delete")) {
            if (words.length == 2) {
                return isNumeric(words[1]) &&
                        validTaskNumberForDelete(words[1]);
            } else {
                throw new MissingArgumentException("Missing task number. Format: delete [task number from 1 to n]");
            }
        }
        return false;
    }

    /**
     * Checks if userInput is in the format "todo [task description]".
     * If not, it will throw exception with meaningful message.
     * @param userInput
     * @return
     * @throws MissingArgumentException
     */
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

    /**
     * Checks if userInput is in the format "deadline [task description] [by date]".
     * If not, it will throw exception with meaningful message.
     * @param userInput
     * @return
     * @throws MissingArgumentException
     */
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

    /**
     * Checks if userInput is in the format "event [task description] [from date] [by date]".
     * If not, it will throw exception with meaningful message.
     * @param userInput
     * @return
     * @throws MissingArgumentException
     * @throws IncorrectFormatException
     */
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

    /**
     * For printing purposes. If number of tasks left is 1, it prints "task", else, "tasks"
     * @param num
     * @return
     */
    public static String tasksLeft(int num) {
        String taskGrammer = "tasks";
        if (num == 1) {
            taskGrammer = "task";
        }
        return "Now you have " + num + " " + taskGrammer + " in the list.";
    }

    /**
     * Checks if num is 0 or 1
     * @param num String
     * @return
     */
    public static Boolean isBinary(String num) {
        if (num.equalsIgnoreCase("1") || num.equalsIgnoreCase("0")) {
            return true;
        } else {
            throw new InvalidTaskNumberException(String.format("Cannot read %d. Use 1 or 0 for task completion", num));
        }
    }

    /**
     * Checks if input is a date by comparing against non-exhaustive list of formatters.
     * @param input
     * @return formatted date
     */
    public static LocalDate isDate(String input) {
        List<DateTimeFormatter> FORMATTERS = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy"),    // 2/12/2019
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),  // 02/12/2019
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),  // 2019-10-15
                DateTimeFormatter.ofPattern("M/d/yyyy"),    // 12/2/2019 (US style)
                DateTimeFormatter.ofPattern("dd-MMM-yyyy"), // 15-Oct-2019
                DateTimeFormatter.ofPattern("d MMM yyyy"),   // 2 Dec 2019
                DateTimeFormatter.ofPattern("MMM d yyyy")   //Dec 2 2019
        );
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try next format
            }
        }
        throw new IncorrectFormatException("Can't read the datetime... try: 6/6/2025");

    }

    /**
     * Checks that each line of file is readable. [Task type] | [Completion status] | [Task description]
     * @param parts
     */
    public static void validateFileLine(String[] parts) {
        if (parts.length < 3) {
            throw new IncorrectFormatException("Line is invalid. Please use: " +
                    "[Task type] | [Completion status] | [Task description]");
        }
        if (!parts[1].equalsIgnoreCase("1") && !parts[1].equalsIgnoreCase("0")) {
            throw new InvalidTaskNumberException(String.format("Cannot read %s. Use 1 or 0 for task completion", parts[1]));
        }
        if (parts[2].isEmpty()) {
            throw new IncorrectFormatException("Task description cannot be empty");
        }
    }

    /**
     * Checks that file line is a readable deadline with format
     * [Task type] | [Completion status] | [Task description] | [by date]
     * @param parts
     */
    public static void validateFileLine_Deadline(String[] parts) {
        if (parts.length < 4) {
            throw new IncorrectFormatException("Line is invalid for task type 'Deadline'. Please use: " +
                    "[Task type] | [Completion status] | [Task description] | [by date]");
        }
    }

    /**
     * Checks that file line is a readable event with format
     * [Task type] | [Completion status] | [Task description] | [from date] | [by date]
     * @param parts
     */
    public static void validateFileLine_Event(String[] parts) {
        if (parts.length < 5) {
            throw new IncorrectFormatException("Line is invalid for task type 'Event'. Please use: " +
                    "[Task type] | [Completion status] | [Task description] | [from date] | [by date]");
        }
    }

    /**
     * Checks that user input has format "due [date]"
     * @param userInput
     * @return
     */
    public static boolean isCheckDue(String userInput) {
        String[] parts = userInput.split("\\s+", 2);
        return parts[0].equalsIgnoreCase("due") && (parts.length == 2);
    }

    /**
     * Returns string of tasks in LIST due on "due" date.
     * @param due
     * @return
     */
    public static String dueOnThisDay(LocalDate due) {
        StringBuilder output = new StringBuilder();

        for (Task task : Constants.LIST) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().equals(due)) {
                    output.append(d.toString()).append("\n\t");
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if ((e.getFrom().isBefore(due) || e.getFrom().equals(due)) &&
                        (e.getTo().isAfter(due) || e.getTo().equals(due))) {
                    output.append(e.toString()).append("\n\t");
                }
            }
        }

        return output.toString().trim();
    }


}