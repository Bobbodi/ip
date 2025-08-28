import Exceptions.InvalidTaskNumberException;
import Resources.Constants;
import Resources.Helper;
import Tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HelperTest {
    @Test
    public void testIsNumeric_validInput() {
        assertTrue(Helper.isNumeric("123"));
        assertTrue(Helper.isNumeric("0"));
    }

    @Test
    public void testIsNumeric_invalidInput() {
        assertFalse(Helper.isNumeric("abc"));
        assertFalse(Helper.isNumeric(""));
    }

    @Test
    void testValidTaskNumber_valid() throws InvalidTaskNumberException {
        Constants.LIST.clear();
        Constants.LIST.add(new Todo("Task 1"));
        Constants.LIST.add(new Todo("Task 2"));
        Constants.LIST.add(new Todo("Task 3"));

        assertTrue(Helper.validTaskNumber("1"));
        assertTrue(Helper.validTaskNumber("3"));
    }

    @Test
    void testValidTaskNumber_invalidTooHigh() {
        Constants.LIST.clear();
        Constants.LIST.add(new Todo("Task 1"));

        InvalidTaskNumberException ex = assertThrows(
                InvalidTaskNumberException.class,
                () -> Helper.validTaskNumber("5")
        );
        assertEquals("5 is not a valid task number.", ex.getMessage());
    }

    @Test
    void testValidTaskNumber_invalidZeroOrNegative() {
        Constants.LIST.clear();
        Constants.LIST.add(new Todo("Task 1"));

        assertThrows(InvalidTaskNumberException.class, () -> Helper.validTaskNumber("0"));
        assertThrows(InvalidTaskNumberException.class, () -> Helper.validTaskNumber("-1"));
    }

        

}