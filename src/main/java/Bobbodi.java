import Exceptions.*;
import Tasks.*;
import Resources.*;

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
        Helper.chatbotSays(Constants.HELLO);
    }

    public static void bye() {
        Helper.chatbotSays(Constants.BYE);
    }

    public static void interact() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                if (userInput.equalsIgnoreCase("list")) {
                    Helper.chatbotSays(Helper.formatLIST());

                } else if (Helper.isMark(userInput)) {
                    String[] words = userInput.split("\\s+");
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    Constants.LIST.get(taskNumber).markDone();
                    Helper.chatbotSays(Constants.MARKASDONE
                            + Constants.LIST.get(taskNumber));

                } else if (Helper.isUnmark(userInput)) {
                    String[] words = userInput.split("\\s+");
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    Constants.LIST.get(taskNumber).markNotDone();
                    Helper.chatbotSays(Constants.MARKNOTDONE
                            + Constants.LIST.get(taskNumber));

                } else if (Helper.isDelete(userInput)) {
                    String[] words = userInput.split("\\s+");
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    Task deletedTask = Constants.LIST.remove(taskNumber);
                    Helper.chatbotSays(Constants.REMOVETASK +
                            deletedTask + "\n" +
                            Helper.tasksLeft(Constants.LIST.size())
                    );

                } else if (Helper.isTodo(userInput)) {
                    String description = userInput.replaceFirst("todo", "").trim();
                    Todo newTodo = new Todo(description);
                    Constants.LIST.add(newTodo);

                    Helper.chatbotSays(Constants.ADDTASK +
                            newTodo + "\n" +
                            Helper.tasksLeft(Constants.LIST.size()));

                } else if (Helper.isDeadline(userInput)) {
                    String[] words = userInput.split("/");
                    String description = words[0].replaceFirst("deadline", "").trim();
                    String by = words[1].replaceFirst("by", "").trim();
                    Deadline newDeadline = new Deadline(description, by);
                    Constants.LIST.add(newDeadline);

                    Helper.chatbotSays(Constants.ADDTASK +
                            newDeadline + "\n" +
                            Helper.tasksLeft(Constants.LIST.size()));

                } else if (Helper.isEvent(userInput)) {
                    String[] words = userInput.split("/");
                    String description = words[0].replaceFirst("event", "").trim();
                    String from = words[1].replaceFirst("from", "").trim();
                    String to = words[2].replaceFirst("to", "").trim();

                    Event newEvent = new Event(description, from, to);
                    Constants.LIST.add(newEvent);

                    Helper.chatbotSays(Constants.ADDTASK +
                            newEvent + "\n" +
                            Helper.tasksLeft(Constants.LIST.size()));

                } else if (!userInput.equalsIgnoreCase("bye")) {
                    Helper.chatbotSays(Constants.ADDED + userInput);
                    Constants.LIST.add(new Task(userInput));
                }
            } catch (IncorrectFormatException | MissingArgumentException | InvalidTaskNumberException | EmptyListException e) {
                Helper.chatbotSays(e.getMessage());
            } catch (Exception e) {
                Helper.chatbotSays("An unexpected error occurred. " + e.getMessage());
            }
        }
        scanner.close();
    }

}
