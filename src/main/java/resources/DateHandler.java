package resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import exceptions.EmptyListException;
import exceptions.IncorrectFormatException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

/**
 * Handle all date related user inputs like checkDueDate, reminder
 */
public class DateHandler {

    /**
     * Handle user input of check due [date]
     */
    public static String checkDue(String userInput) {
        String[] parts = userInput.split("\\s+", 2);
        LocalDate checkDate = isDate(parts[1]);
        return (Constants.DUEONTHISDAY + checkDate + "\n"
                + dueOnThisDay(checkDate));
    }

    /**
     * Return string of tasks due in x days after processing user input
     */
    public static String remind(String userInput) {
        String[] parts = userInput.split("\\s+", 2);
        String day = parts[1];
        return Constants.REMINDERS
                + dueInXDays(Integer.parseInt(day));
    }

    /**
     * Get all the tasks that are due in 0 <= x <= days
     * @param days upper bound
     * @return String of all the tasks, with the number of days they are due in
     */
    public static String dueInXDays(int days) throws EmptyListException {
        if (Constants.LIST.isEmpty()) {
            throw new EmptyListException("Oops! There are no tasks!");
        }
        assert !Constants.LIST.isEmpty() : "LIST is empty";

        LocalDate today = LocalDate.now();
        StringBuilder listOfTasks = new StringBuilder();

        for (int i = 0; i <= days; i++) {
            LocalDate targetDate = today.plusDays(i);
            String tasksDue = dueOnThisDay(targetDate);
            if (!tasksDue.isEmpty()) {
                listOfTasks.append("Due on ").append(targetDate).append(":\n");
                listOfTasks.append(tasksDue);
            }
        }

        return listOfTasks.toString();
    }

    /**
     * Returns string of tasks in LIST due on "due" date.
     * @param due for this day
     * @return string of tasks due on this day
     */
    public static String dueOnThisDay(LocalDate due) {
        StringBuilder output = new StringBuilder();

        for (Task task : Constants.LIST) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().equals(due)) {
                    output.append("\t").append(d.toString()).append("\n");
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if ((e.getFrom().isBefore(due) || e.getFrom().equals(due))
                        && (e.getTo().isAfter(due) || e.getTo().equals(due))) {
                    output.append("\t").append(e.toString()).append("\n");
                }
            }
        }

        return output.toString();
    }

    /**
     * Checks if input is a date by comparing against non-exhaustive list of formatters.
     * @param input for input
     * @return formatted date
     */
    public static LocalDate isDate(String input) {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy"), // 2/12/2019
                DateTimeFormatter.ofPattern("dd/MM/yyyy"), // 02/12/2019
                DateTimeFormatter.ofPattern("yyyy-MM-dd"), // 2019-10-15
                DateTimeFormatter.ofPattern("M/d/yyyy"), // 12/2/2019 (US style)
                DateTimeFormatter.ofPattern("dd-MMM-yyyy"), // 15-Oct-2019
                DateTimeFormatter.ofPattern("d MMM yyyy"), // 2 Dec 2019
                DateTimeFormatter.ofPattern("MMM d yyyy") //Dec 2 2019
        );
        input = input.trim();
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try next format
            }
        }
        throw new IncorrectFormatException(String.format("Can't read the datetime %s... try: 6/6/2025", input));
    }


}
