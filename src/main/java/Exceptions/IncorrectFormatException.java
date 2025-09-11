package Exceptions;

/**
 * When inputting a todo, deadline, event, task but the format is wrong
 */
public class IncorrectFormatException extends BobbodiException {

    public IncorrectFormatException(String msg) {
        super(msg);
    }

}
