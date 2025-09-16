package exceptions;

/**
 * If trying to access LIST, but it is empty
 */
public class EmptyListException extends BobbodiException {

    public EmptyListException(String msg) {
        super(msg);
    }

}
