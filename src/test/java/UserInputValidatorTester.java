import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.IncorrectFormatException;
import exceptions.MissingArgumentException;
import resources.Constants;
import resources.UserInputValidator;
import tasks.Todo;

/**
 * tests for all user input validator functions
 */
public class UserInputValidatorTester {

    @BeforeEach
    void setUp() {
        UserInputValidator validator = new UserInputValidator();
    }

    @Test
    @DisplayName("Test isBye with valid inputs")
    void testIsByeValidInputsReturnsTrue() {
        assertTrue(UserInputValidator.isBye("bye"));
        assertTrue(UserInputValidator.isBye("BYE"));
        assertTrue(UserInputValidator.isBye("ByE"));
    }

    @Test
    @DisplayName("Test isBye with invalid inputs")
    void testIsByeInvalidInputsReturnsFalse() {
        assertFalse(UserInputValidator.isBye("hello"));
        assertFalse(UserInputValidator.isBye("byee"));
        assertFalse(UserInputValidator.isBye(" good bye "));
    }

    @Test
    @DisplayName("Test isList with valid inputs")
    void testIsListValidInputsReturnsTrue() {
        assertTrue(UserInputValidator.isList("list"));
        assertTrue(UserInputValidator.isList("LIST"));
        assertTrue(UserInputValidator.isList("List"));
    }

    @Test
    @DisplayName("Test isList with invalid inputs")
    void testIsListInvalidInputsReturnsFalse() {
        assertFalse(UserInputValidator.isList("hello"));
        assertFalse(UserInputValidator.isList("list "));
        assertFalse(UserInputValidator.isList(" list"));
    }

    @Test
    @DisplayName("Test isOthers with valid inputs")
    void testIsOthersValidInputsReturnsTrue() {
        assertTrue(UserInputValidator.isOthers("hello"));
        assertTrue(UserInputValidator.isOthers("add task"));
        assertTrue(UserInputValidator.isOthers("LIST ")); // Note: trailing space
    }

    @Test
    @DisplayName("Test isOthers with invalid inputs")
    void testIsOthersInvalidInputsReturnsFalse() {
        assertFalse(UserInputValidator.isOthers("bye"));
        assertFalse(UserInputValidator.isOthers("BYE"));
        assertFalse(UserInputValidator.isOthers(""));
    }

    @Test
    @DisplayName("Test isOthers with empty and blank inputs")
    void testIsOthersEmptyAndBlankInputsReturnsFalse() {
        assertFalse(UserInputValidator.isOthers(""));
        assertFalse(UserInputValidator.isOthers(" "));
        assertFalse(UserInputValidator.isOthers("     "));
        assertFalse(UserInputValidator.isOthers("\t"));
        assertFalse(UserInputValidator.isOthers("\n"));
    }

    @Test
    @DisplayName("Test isLoadFile with valid inputs")
    void testIsLoadFileValidInputsReturnsTrue() {
        assertTrue(UserInputValidator.isLoadFile("load file.txt"));
        assertTrue(UserInputValidator.isLoadFile("LOAD data.csv"));
        assertTrue(UserInputValidator.isLoadFile("Load  path with spaces"));
    }

    @Test
    @DisplayName("Test isLoadFile with invalid inputs")
    void testIsLoadFileInvalidInputsReturnsFalse() {
        assertFalse(UserInputValidator.isLoadFile("load"));
        assertFalse(UserInputValidator.isLoadFile("load "));
        assertFalse(UserInputValidator.isLoadFile("download file.txt"));
        assertFalse(UserInputValidator.isLoadFile(""));
        assertFalse(UserInputValidator.isLoadFile("list"));
    }

    @Test
    @DisplayName("Test isMark with valid inputs")
    void testIsMarkValidInputsReturnsTrue() throws MissingArgumentException {
        // Note: This assumes Helper.isNumeric() and Helper.validTaskNumber() return true for these inputs
        // You might need to mock or setup the Helper class for proper testing
        Constants.LIST.clear();
        Constants.LIST.add(new Todo("Task 1"));
        Constants.LIST.add(new Todo("Task 2"));
        Constants.LIST.add(new Todo("Task 3"));
        Constants.LIST.add(new Todo("Task 4"));
        Constants.LIST.add(new Todo("Task 5"));
        Constants.LIST.add(new Todo("Task 6"));
        assertTrue(UserInputValidator.isMark("mark 1"));
        assertTrue(UserInputValidator.isMark("MARK 2"));
        assertTrue(UserInputValidator.isMark("Mark 6"));
    }

    @Test
    @DisplayName("Test isMark with missing argument")
    void testIsMarkMissingArgumentThrowsException() {
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isMark("mark"));
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isMark("mark "));
    }

    @Test
    @DisplayName("Test isMark with invalid command")
    void testIsMarkInvalidCommandReturnsFalse() throws MissingArgumentException {
        assertFalse(UserInputValidator.isMark("mar 1"));
        assertFalse(UserInputValidator.isMark("markk 1"));
        assertFalse(UserInputValidator.isMark("hello 1"));
        assertFalse(UserInputValidator.isMark("list 1"));
    }

    @Test
    @DisplayName("Test isUnmark with valid inputs")
    void testIsUnmarkValidInputsReturnsTrue() throws MissingArgumentException {
        // Note: This assumes Helper methods return true for these inputs
        Constants.LIST.clear();
        Constants.LIST.add(new Todo("Task 1"));
        Constants.LIST.add(new Todo("Task 2"));
        Constants.LIST.add(new Todo("Task 3"));
        Constants.LIST.add(new Todo("Task 4"));
        Constants.LIST.add(new Todo("Task 5"));
        Constants.LIST.add(new Todo("Task 6"));
        assertTrue(UserInputValidator.isUnmark("unmark 1"));
        assertTrue(UserInputValidator.isUnmark("UNMARK 2"));
        assertTrue(UserInputValidator.isUnmark("Unmark 6"));
    }

    @Test
    @DisplayName("Test isUnmark with missing argument")
    void testIsUnmarkMissingArgumentThrowsException() {
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isUnmark("unmark"));
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isUnmark("unmark "));
    }

    @Test
    @DisplayName("Test isUnmark with invalid command")
    void testIsUnmarkInvalidCommandReturnsFalse() throws MissingArgumentException {
        assertFalse(UserInputValidator.isUnmark("unmar 1"));
        assertFalse(UserInputValidator.isUnmark("unmarkk 1"));
        assertFalse(UserInputValidator.isUnmark("hello 1"));
    }

    @Test
    @DisplayName("Test isDelete with valid inputs")
    void testIsDeleteValidInputsReturnsTrue() throws MissingArgumentException {
        // Note: This assumes Helper methods return true for these inputs
        Constants.LIST.clear();
        Constants.LIST.add(new Todo("Task 1"));
        Constants.LIST.add(new Todo("Task 2"));
        Constants.LIST.add(new Todo("Task 3"));
        Constants.LIST.add(new Todo("Task 4"));
        Constants.LIST.add(new Todo("Task 5"));
        Constants.LIST.add(new Todo("Task 6"));
        assertTrue(UserInputValidator.isDelete("delete 1"));
        assertTrue(UserInputValidator.isDelete("DELETE 2"));
        assertTrue(UserInputValidator.isDelete("Delete 6"));
    }

    @Test
    @DisplayName("Test isDelete with missing argument")
    void testIsDeleteMissingArgumentThrowsException() {
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isDelete("delete"));
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isDelete("delete "));
    }

    @Test
    @DisplayName("Test isDelete with invalid command")
    void testIsDeleteInvalidCommandReturnsFalse() throws MissingArgumentException {
        assertFalse(UserInputValidator.isDelete("delet 1"));
        assertFalse(UserInputValidator.isDelete("deletee 1"));
        assertFalse(UserInputValidator.isDelete("hello 1"));
    }

    @Test
    @DisplayName("Test isReminder with valid inputs")
    void testIsReminderValidInputsReturnsTrue() {
        assertTrue(UserInputValidator.isReminder("reminder 7"));
        assertTrue(UserInputValidator.isReminder("REMINDER 1"));
        assertTrue(UserInputValidator.isReminder("Reminder 30"));
    }

    @Test
    @DisplayName("Test isReminder with invalid inputs")
    void testIsReminderInvalidInputsReturnsFalse() {
        assertFalse(UserInputValidator.isReminder("reminder"));
        assertFalse(UserInputValidator.isReminder("reminder "));
        assertFalse(UserInputValidator.isReminder("reminder abc"));
        assertFalse(UserInputValidator.isReminder("remind 7"));
        assertFalse(UserInputValidator.isReminder("list 7"));
    }

    @Test
    @DisplayName("Test isCheckDue with valid inputs")
    void testIsCheckDueValidInputsReturnsTrue() {
        assertTrue(UserInputValidator.isCheckDue("due 2023-12-31"));
        assertTrue(UserInputValidator.isCheckDue("DUE tomorrow"));
        assertTrue(UserInputValidator.isCheckDue("Due next week"));
    }

    @Test
    @DisplayName("Test isCheckDue with invalid inputs")
    void testIsCheckDueInvalidInputsReturnsFalse() {
        assertFalse(UserInputValidator.isCheckDue("due"));
        assertFalse(UserInputValidator.isCheckDue("due "));
        assertFalse(UserInputValidator.isCheckDue("dues 2023-12-31"));
        assertFalse(UserInputValidator.isCheckDue("list 2023-12-31"));
    }

    @Test
    @DisplayName("Test isTodo with valid inputs")
    void testIsTodoValidInputsReturnsTrue() throws MissingArgumentException {
        assertTrue(UserInputValidator.isTodo("todo read book"));
        assertTrue(UserInputValidator.isTodo("TODO exercise"));
        assertTrue(UserInputValidator.isTodo("Todo  complete assignment"));
    }

    @Test
    @DisplayName("Test isTodo with missing argument")
    void testIsTodoMissingArgumentThrowsException() {
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isTodo("todo"));
        assertThrows(MissingArgumentException.class, () -> UserInputValidator.isTodo("todo "));
    }

    @Test
    @DisplayName("Test isTodo with invalid command")
    void testIsTodoInvalidCommandReturnsFalse() throws MissingArgumentException {
        assertFalse(UserInputValidator.isTodo("todos read book"));
        assertFalse(UserInputValidator.isTodo("to do exercise"));
        assertFalse(UserInputValidator.isTodo("list read book"));
    }

    @Test
    @DisplayName("Test isDeadline with valid inputs")
    void testIsDeadlineValidInputsReturnsTrue() throws MissingArgumentException, IncorrectFormatException {
        assertTrue(UserInputValidator.isDeadline("deadline return book /by 2023-12-31"));
        assertTrue(UserInputValidator.isDeadline("DEADLINE submit assignment /by tomorrow"));
        assertTrue(UserInputValidator.isDeadline("Deadline  complete project /by next week"));
    }

    @Test
    @DisplayName("Test isDeadline with missing description")
    void testIsDeadlineMissingDescriptionThrowsException() {
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isDeadline("deadline"));
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isDeadline("deadline "));
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isDeadline("deadline /by tomorrow"));
    }

    @Test
    @DisplayName("Test isDeadline with missing /by")
    void testIsDeadlineMissingByThrowsException() {
        assertThrows(IncorrectFormatException.class, () ->
                UserInputValidator.isDeadline("deadline return book tomorrow"));
    }

    @Test
    @DisplayName("Test isDeadline with missing by date")
    void testIsDeadlineMissingByDateThrowsException() {
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isDeadline("deadline return book /by"));
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isDeadline("deadline return book /by "));
    }

    @Test
    @DisplayName("Test isDeadline with invalid command")
    void testIsDeadlineInvalidCommandReturnsFalse() throws MissingArgumentException, IncorrectFormatException {
        assertFalse(UserInputValidator.isDeadline("deadlines return book /by tomorrow"));
        assertFalse(UserInputValidator.isDeadline("list return book /by tomorrow"));
    }

    @Test
    @DisplayName("Test isEvent with valid inputs")
    void testIsEventValidInputsReturnsTrue() throws MissingArgumentException, IncorrectFormatException {
        assertTrue(UserInputValidator.isEvent("event meeting /from 2pm /to 4pm"));
        assertTrue(UserInputValidator.isEvent("EVENT conference /from Monday /to Friday"));
        assertTrue(UserInputValidator.isEvent("Event  party /from 8pm /to 11pm"));
    }

    @Test
    @DisplayName("Test isEvent with missing description")
    void testIsEventMissingDescriptionThrowsException() {
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isEvent("event"));
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isEvent("event "));
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isEvent("event /from 2pm /to 4pm"));
    }

    @Test
    @DisplayName("Test isEvent with missing /from")
    void testIsEventMissingFromThrowsException() {
        assertThrows(IncorrectFormatException.class, () ->
                UserInputValidator.isEvent("event meeting 2pm /to 4pm"));
    }

    @Test
    @DisplayName("Test isEvent with missing /to")
    void testIsEventMissingToThrowsException() {
        assertThrows(IncorrectFormatException.class, () ->
                UserInputValidator.isEvent("event meeting /from 2pm 4pm"));
    }

    @Test
    @DisplayName("Test isEvent with missing from date")
    void testIsEventMissingFromDateThrowsException() {
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isEvent("event meeting /from /to 4pm"));
    }

    @Test
    @DisplayName("Test isEvent with missing to date")
    void testIsEventMissingToDateThrowsException() {
        assertThrows(MissingArgumentException.class, () ->
                UserInputValidator.isEvent("event meeting /from 2pm /to"));
    }

    @Test
    @DisplayName("Test isEvent with invalid command")
    void testIsEventInvalidCommandReturnsFalse() throws MissingArgumentException, IncorrectFormatException {
        assertFalse(UserInputValidator.isEvent("events meeting /from 2pm /to 4pm"));
        assertFalse(UserInputValidator.isEvent("list meeting /from 2pm /to 4pm"));
    }

    @Test
    @DisplayName("Test isFind with valid inputs")
    void testIsFindValidInputsReturnsTrue() {
        assertTrue(UserInputValidator.isFind("find book"));
        assertTrue(UserInputValidator.isFind("FIND meeting"));
        assertTrue(UserInputValidator.isFind("Find  important task"));
    }

    @Test
    @DisplayName("Test isFind with invalid inputs")
    void testIsFindInvalidInputsReturnsFalse() {
        assertFalse(UserInputValidator.isFind("find"));
        assertFalse(UserInputValidator.isFind("find "));
        assertFalse(UserInputValidator.isFind("finding book"));
        assertFalse(UserInputValidator.isFind("list book"));
    }

}
