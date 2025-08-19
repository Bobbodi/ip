import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Bobbodi {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        interact();
        bye();
    }

    public final static String DASHES = "---------------------------------------------";
    public final static String CHATBOT_NAME = "Bobbodi";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String SEPARATOR = BLUE + DASHES + RESET;
    public static final List<Task> LIST = new ArrayList<Task>();

    public static String formatLIST() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < LIST.size(); i++) {
            String numbering = String.format("%2d. ", i+1);
            text.append(numbering).append(LIST.get(i));
            if (i < LIST.size() - 1) {
                text.append("\n");
            }
        }
        return text.toString();
    }

    public static void chatbotSays(String text) {
        System.out.println(SEPARATOR);
        System.out.println(PURPLE + text + RESET);
        System.out.println(SEPARATOR);
    }

    public static boolean isNumeric(String text) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(text, pos);
        return text.length() == pos.getIndex();
    }

    public static boolean validTaskNumber(String str) {
        return Integer.parseInt(str) <= LIST.size() &&
                Integer.parseInt(str) > 0;
    }
    public static boolean isMark(String userInput) {
        //e.g. mark 2
        String[] words = userInput.split(" ");
        return words[0].equalsIgnoreCase("mark") &&
                isNumeric(words[1]) &&
                words.length == 2 &&
                validTaskNumber(words[1]);
    }

    public static boolean isUnmark(String userInput) {
        //e.g. mark 2
        String[] words = userInput.split(" ");
        return words[0].equalsIgnoreCase("unmark") &&
                isNumeric(words[1]) &&
                words.length == 2 &&
                validTaskNumber(words[1]);
    }

    public static void greeting() {
        chatbotSays("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
    }

    public static void bye() {
        chatbotSays("Bye. Hope to see you again soon!");
    }

    public static void interact() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);

        while (!userInput.equalsIgnoreCase("bye")) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("list")) {
                chatbotSays(formatLIST());

            } else if (isMark(userInput)) {
                String[] words = userInput.split(" ");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                LIST.get(taskNumber).markDone();
                chatbotSays("Nice! I've marked this task as done:\n"
                        + LIST.get(taskNumber));

            } else if (isUnmark(userInput)) {
                String[] words = userInput.split(" ");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                LIST.get(taskNumber).markNotDone();
                chatbotSays("Ok, I've marked this task as not done yet:\n"
                        + LIST.get(taskNumber));

            } else if (!userInput.equalsIgnoreCase("bye")) {
                chatbotSays("added: " + userInput);
                LIST.add(new Task(userInput));
            }
        }

        scanner.close();
    }

}
