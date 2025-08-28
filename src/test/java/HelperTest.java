import Resources.Helper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}