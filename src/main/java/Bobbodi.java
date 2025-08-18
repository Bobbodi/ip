import java.util.Scanner;

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

    public static void greeting() {
        System.out.println(BLUE + DASHES + RESET);
        System.out.println(PURPLE + "Hello! I'm " + CHATBOT_NAME + RESET);
        System.out.println(PURPLE + "What can I do for you?" + RESET);
        System.out.println(BLUE + DASHES + RESET);
    }

    public static void bye() {
        System.out.println(BLUE + DASHES + RESET);
        System.out.println(PURPLE + "Bye. Hope to see you again soon!" + RESET);
        System.out.println(BLUE + DASHES + RESET);
    }

    public static void interact() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);

        while (!userInput.equalsIgnoreCase("bye")) {
            userInput = scanner.nextLine().trim();
            //System.out.println(PURPLE + userInput + RESET);
            if (!userInput.equalsIgnoreCase("bye")) {
                System.out.println(BLUE + DASHES + RESET);
                System.out.println(PURPLE + userInput + RESET);
                System.out.println(BLUE + DASHES + RESET);
            }
        }

        scanner.close();
    }

}
