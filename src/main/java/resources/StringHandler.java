package resources;

import tasks.Task;

/**
 * Abstract out functions related to processing string inputs like
 * mark x, delete x, unmark x
 */
public class StringHandler {

    /**
     * Process input: delete x
     */
    public static String delete(String userInput) {
        String[] words = userInput.split("\\s+");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        Task deletedTask = Constants.LIST.remove(taskNumber);
        return (Constants.REMOVETASK
                + deletedTask + "\n"
                + Helper.tasksLeft(Constants.LIST.size()));
    }

    /**
     * Process input: unmark x
     */
    public static String unmark(String userInput) {
        String[] words = userInput.split("\\s+");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        Constants.LIST.get(taskNumber).markNotDone();
        return (Constants.MARKNOTDONE
                + Constants.LIST.get(taskNumber));
    }

    /**
     * Process input: mark x
     */
    public static String mark(String userInput) {
        String[] words = userInput.split("\\s+");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        Constants.LIST.get(taskNumber).markDone();
        return (Constants.MARKASDONE
                + Constants.LIST.get(taskNumber));
    }

    /**
     * Process input: find x
     */
    public static String find(String userInput) {
        return (Constants.FINDRESULTS + "\n"
                + findResults(userInput));
    }

    /**
     * use search to find matches according to user input
     * @param userInput for user input
     * @return matches as a string
     */
    public static String findResults(String userInput) {
        String[] parts = userInput.split("\\s+", 2);
        if (parts[1].isEmpty()) {
            return "Nothing here...";
        }

        String search = parts[1].toLowerCase();
        StringBuilder results = new StringBuilder();

        for (Task task : Constants.LIST) {
            if (task.getDescription().toLowerCase().contains(search)) {
                if (!results.isEmpty()) {
                    results.append("\n"); // add newline before appending next task
                }
                results.append("\t").append(task.toString());
            }
        }
        return !results.isEmpty()
                ? results.toString()
                : "No matches...";
    }
}
