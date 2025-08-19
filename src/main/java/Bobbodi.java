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

    public static void greeting() {
        Helper.chatbotSays("Hello! I'm " + Constants.CHATBOT_NAME + "\nWhat can I do for you?");
    }

    public static void bye() {
        Helper.chatbotSays("Bye. Hope to see you again soon!");
    }

    public static void interact() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);

        while (!userInput.equalsIgnoreCase("bye")) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("list")) {
                Helper.chatbotSays(Helper.formatLIST());

            } else if (Helper.isMark(userInput)) {
                String[] words = userInput.split(" ");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Constants.LIST.get(taskNumber).markDone();
                Helper.chatbotSays("Nice! I've marked this task as done:\n"
                        + Constants.LIST.get(taskNumber));

            } else if (Helper.isUnmark(userInput)) {
                String[] words = userInput.split(" ");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Constants.LIST.get(taskNumber).markNotDone();
                Helper.chatbotSays("Ok, I've marked this task as not done yet:\n"
                        + Constants.LIST.get(taskNumber));

            } else if (Helper.isTodo(userInput)) {
                String[] words = userInput.split(" ");
                Todo newTodo = new Todo(words[1]);
                Constants.LIST.add(newTodo);

                Helper.chatbotSays("Got it. I've added this task: \n\t" +
                        newTodo + "\n" +
                        Helper.tasksLeft(Constants.LIST.size()));

            } else if (Helper.isDeadline(userInput)) {
                String[] words = userInput.split("/");
                String description = words[0].replaceFirst("deadline", "").trim();
                String by = words[1].replaceFirst("by", "").trim();
                Deadline newDeadline = new Deadline(description, by);
                Constants.LIST.add(newDeadline);

                Helper.chatbotSays("Got it. I've added this task: \n\t" +
                        newDeadline + "\n" +
                        Helper.tasksLeft(Constants.LIST.size()));

            } else if (Helper.isEvent(userInput)) {
                String[] words = userInput.split("/");
                String description = words[0].replaceFirst("event", "").trim();
                String from = words[1].replaceFirst("from", "").trim();
                String to = words[2].replaceFirst("to", "").trim();

                Event newEvent = new Event(description, from, to);
                Constants.LIST.add(newEvent);

                Helper.chatbotSays("Got it. I've added this task: \n\t" +
                        newEvent + "\n" +
                        Helper.tasksLeft(Constants.LIST.size()));

            } else if (!userInput.equalsIgnoreCase("bye")) {
                Helper.chatbotSays("added: " + userInput);
                Constants.LIST.add(new Task(userInput));
            }
        }

        scanner.close();
    }

}
