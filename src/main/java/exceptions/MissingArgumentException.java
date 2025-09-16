package exceptions;

/**
 * When creating a todo, deadline, event task, but one of the fields is missing
 */
public class MissingArgumentException extends BobbodiException {

    public MissingArgumentException(String msg) {
        super(msg);
    }

}
