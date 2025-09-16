import exceptions.EmptyListException;
import exceptions.IncorrectFormatException;
import exceptions.InvalidTaskNumberException;
import exceptions.MissingArgumentException;
import resources.Constants;
import resources.DateHandler;
import resources.FileHandler;
import resources.Helper;
import resources.StringHandler;
import resources.UserInputValidator;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Main class
 */
public class Bobbodi {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //greeting();
        //loadfile("data.txt");
        //interact();
        //bye();
    }

    /**
     * getResponse for javaFX
     */
    public String getResponse(String userInput) {

        try {
            if (UserInputValidator.isBye(userInput)) {
                return (Constants.BYE);

            } else if (UserInputValidator.isList(userInput)) {
                return (Helper.formatList());

            } else if (UserInputValidator.isLoadFile(userInput)) {
                return FileHandler.loadfile(userInput);

            } else if (UserInputValidator.isCheckDue(userInput)) {
                return DateHandler.checkDue(userInput);

            } else if (UserInputValidator.isFind(userInput)) {
                return StringHandler.find(userInput);

            } else if (UserInputValidator.isMark(userInput)) {
                return StringHandler.mark(userInput);

            } else if (UserInputValidator.isUnmark(userInput)) {
                return StringHandler.unmark(userInput);

            } else if (UserInputValidator.isDelete(userInput)) {
                return StringHandler.delete(userInput);

            } else if (UserInputValidator.isTodo(userInput)) {
                return Todo.respondTo(userInput);

            } else if (UserInputValidator.isDeadline(userInput)) {
                return Deadline.respondTo(userInput);

            } else if (UserInputValidator.isEvent(userInput)) {
                return Event.respondTo(userInput);

            } else if (UserInputValidator.isReminder(userInput)) {
                return DateHandler.remind(userInput);

            } else if (UserInputValidator.isOthers(userInput)) {
                return Constants.CONFUSED;
            }

        } catch (IncorrectFormatException | MissingArgumentException
                 | InvalidTaskNumberException | EmptyListException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred. " + e.getMessage();
        }
        return "I don't understand...";
    }

}

