package Exceptions;

/**
 * When having a task number that is not between 1 and the length of the list
 */
public class InvalidTaskNumberException extends BobbodiException {

    public InvalidTaskNumberException(String msg) {
        super(msg);
    }

}
