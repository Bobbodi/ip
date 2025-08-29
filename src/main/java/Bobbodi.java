import Exceptions.*;
import Tasks.*;
import Resources.*;

import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Bobbodi {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //greeting();
        //loadfile("data.txt");
        //interact();
        //bye();
    }

    /**
     * getResponse for javaFX
     */
    public String getResponse(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            return (Constants.BYE);
        }

        try {
            if (userInput.equalsIgnoreCase("list")) {
                return (Helper.formatLIST());

            } else if (Helper.isLoadFile(userInput)) {
                String[] parts = userInput.split("\\s+", 2);
                String filename = parts[1].trim();
                return loadfile(filename);

            } else if (Helper.isCheckDue(userInput)) {
                String[] parts = userInput.split("\\s+", 2);
                LocalDate checkDate = Helper.isDate(parts[1]);
                return (Constants.DUEONTHISDAY + checkDate + "\n" +
                        Helper.dueOnThisDay(checkDate));

            } else if (Helper.isFind(userInput)) {
                return (Constants.FINDRESULTS + "\n" +
                        Helper.findResults(userInput));

            } else if (Helper.isMark(userInput)) {
                String[] words = userInput.split("\\s+");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Constants.LIST.get(taskNumber).markDone();
                return (Constants.MARKASDONE
                        + Constants.LIST.get(taskNumber));

            } else if (Helper.isUnmark(userInput)) {
                String[] words = userInput.split("\\s+");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Constants.LIST.get(taskNumber).markNotDone();
                return (Constants.MARKNOTDONE
                        + Constants.LIST.get(taskNumber));

            } else if (Helper.isDelete(userInput)) {
                String[] words = userInput.split("\\s+");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Task deletedTask = Constants.LIST.remove(taskNumber);
                return (Constants.REMOVETASK +
                        deletedTask + "\n" +
                        Helper.tasksLeft(Constants.LIST.size())
                );

            } else if (Helper.isTodo(userInput)) {
                String description = userInput.replaceFirst("todo", "").trim();
                Todo newTodo = new Todo(description);
                Constants.LIST.add(newTodo);

                return (Constants.ADDTASK
                        + newTodo + "\n" + Helper.tasksLeft(Constants.LIST.size()));

            } else if (Helper.isDeadline(userInput)) {
                String[] parts = userInput.split("/by", 2);
                String description = parts[0].replaceFirst("deadline", "").trim();
                String by = parts[1].trim();

                LocalDate byDate = Helper.isDate(by);
                Deadline newDeadline = new Deadline(description, byDate);
                Constants.LIST.add(newDeadline);

                return (Constants.ADDTASK +
                        newDeadline + "\n" +
                        Helper.tasksLeft(Constants.LIST.size()));

            } else if (Helper.isEvent(userInput)) {
                String[] fromSplit = userInput.split("/from", 2);
                String description = fromSplit[0].replaceFirst("event", "").trim();

                String[] toSplit = fromSplit[1].split("/to", 2);
                String from = toSplit[0].trim();
                String to = toSplit[1].trim();

                LocalDate fromDate = Helper.isDate(from);
                LocalDate toDate = Helper.isDate(to);
                Event newEvent = new Event(description, fromDate, toDate);
                Constants.LIST.add(newEvent);

                return (Constants.ADDTASK +
                        newEvent + "\n" +
                        Helper.tasksLeft(Constants.LIST.size()));

            } else if (!userInput.equalsIgnoreCase("bye") && !userInput.isEmpty()) {
                Constants.LIST.add(new Task(userInput));
                return (Constants.ADDED + userInput);
            }
        } catch (IncorrectFormatException | MissingArgumentException | InvalidTaskNumberException | EmptyListException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred. " + e.getMessage();
        }
        return "";
    }
    /**
     * Prints HELLO on console.
     */
    public static String greeting() {
        return (Constants.HELLO);
    }

    /**
     * Reads and loads tasks from file. The filepath is relative to the base location.
     * Once loaded, it prints "All in!".
     * @param filePath
     */
    public static String loadfile(String filePath) {
        try {
            String output = String.format("Reading file %s...", filePath);
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" - ");
                Helper.validateFileLine(parts);
                switch (parts[0]) {
                case "T":
                    Todo todo = new Todo(parts[2]);
                    todo.setDone(parts[1]);
                    Constants.LIST.add(todo);
                    break;
                case "D":
                    Helper.validateFileLine_Deadline(parts);
                    LocalDate byDate = Helper.isDate(parts[3]);
                    Deadline deadline = new Deadline(parts[2], byDate);
                    deadline.setDone(parts[1]);
                    Constants.LIST.add(deadline);
                    break;
                case "E":
                    Helper.validateFileLine_Event(parts);
                    LocalDate fromDate = Helper.isDate(parts[3]);
                    LocalDate byDate1 = Helper.isDate(parts[4]);
                    Event event = new Event(parts[2], fromDate, byDate1);
                    event.setDone(parts[1]);
                    Constants.LIST.add(event);
                    break;
                default:
                    throw new IncorrectFormatException(String.format("Unknown task type %s", parts[0]));
                }
            }
            output += (Constants.LOADED);
            return output;
        } catch (FileNotFoundException e) {
            return (String.format("File %s not found", filePath));
        } catch (IncorrectFormatException | InvalidTaskNumberException | MissingArgumentException e) {
            return (e.getMessage());
        }
    }

    /**
     * Prints BYE on console
     */
    public static String bye() {
        return (Constants.BYE);
    }

//    /**
//     * Handles user interaction with chatbot. Keeps waiting for user input until it receives 'bye'.
//     * Handles: bye, list, mark [task number], unmark [task number], delete [task number], todo [task description],
//     * deadline [task description] [by date], event [task description] [from date] [to date], due [date]
//     */
//    public static void interact() {
//        String userInput = "";
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNextLine()) {
//            userInput = scanner.nextLine().trim();
//            getResponse(userInput);
//            if (userInput.equalsIgnoreCase("bye")) {
//                break;
//            }
//
//            try {
//                if (userInput.equalsIgnoreCase("list")) {
//                    Helper.chatbotSays(Helper.formatLIST());
//
//                } else if (Helper.isCheckDue(userInput)) {
//                    String[] parts = userInput.split("\\s+", 2);
//                    LocalDate checkDate = Helper.isDate(parts[1]);
//                    Helper.chatbotSays(Constants.DUEONTHISDAY + checkDate + "\n" +
//                            Helper.dueOnThisDay(checkDate));
//
//                } else if (Helper.isFind(userInput)) {
//                    Helper.chatbotSays(Constants.FINDRESULTS + "\n" +
//                            Helper.findResults(userInput));
//
//                } else if (Helper.isMark(userInput)) {
//                    String[] words = userInput.split("\\s+");
//                    int taskNumber = Integer.parseInt(words[1]) - 1;
//                    Constants.LIST.get(taskNumber).markDone();
//                    Helper.chatbotSays(Constants.MARKASDONE
//                            + Constants.LIST.get(taskNumber));
//
//                } else if (Helper.isUnmark(userInput)) {
//                    String[] words = userInput.split("\\s+");
//                    int taskNumber = Integer.parseInt(words[1]) - 1;
//                    Constants.LIST.get(taskNumber).markNotDone();
//                    Helper.chatbotSays(Constants.MARKNOTDONE
//                            + Constants.LIST.get(taskNumber));
//
//                } else if (Helper.isDelete(userInput)) {
//                    String[] words = userInput.split("\\s+");
//                    int taskNumber = Integer.parseInt(words[1]) - 1;
//                    Task deletedTask = Constants.LIST.remove(taskNumber);
//                    Helper.chatbotSays(Constants.REMOVETASK +
//                            deletedTask + "\n" +
//                            Helper.tasksLeft(Constants.LIST.size())
//                    );
//
//                } else if (Helper.isTodo(userInput)) {
//                    String description = userInput.replaceFirst("todo", "").trim();
//                    Todo newTodo = new Todo(description);
//                    Constants.LIST.add(newTodo);
//
//                    Helper.chatbotSays(Constants.ADDTASK
//                           + newTodo + "\n" + Helper.tasksLeft(Constants.LIST.size()));
//
//                } else if (Helper.isDeadline(userInput)) {
//                    String[] words = userInput.split("/");
//                    String description = words[0].replaceFirst("deadline", "").trim();
//                    String by = words[1].replaceFirst("by", "").trim();
//                    LocalDate byDate = Helper.isDate(by);
//                    Deadline newDeadline = new Deadline(description, byDate);
//                    Constants.LIST.add(newDeadline);
//
//                    Helper.chatbotSays(Constants.ADDTASK +
//                            newDeadline + "\n" +
//                            Helper.tasksLeft(Constants.LIST.size()));
//
//                } else if (Helper.isEvent(userInput)) {
//                    String[] words = userInput.split("/");
//                    String description = words[0].replaceFirst("event", "").trim();
//                    String from = words[1].replaceFirst("from", "").trim();
//                    String to = words[2].replaceFirst("to", "").trim();
//                    LocalDate fromDate = Helper.isDate(from);
//                    LocalDate toDate = Helper.isDate(to);
//                    Event newEvent = new Event(description, fromDate, toDate);
//                    Constants.LIST.add(newEvent);
//
//                    Helper.chatbotSays(Constants.ADDTASK +
//                            newEvent + "\n" +
//                            Helper.tasksLeft(Constants.LIST.size()));
//
//                } else if (!userInput.equalsIgnoreCase("bye") && !userInput.isEmpty()) {
//                    Helper.chatbotSays(Constants.ADDED + userInput);
//                    Constants.LIST.add(new Task(userInput));
//                }
//            } catch (IncorrectFormatException | MissingArgumentException | InvalidTaskNumberException |
//            EmptyListException e) {
//                Helper.chatbotSays(e.getMessage());
//            } catch (Exception e) {
//                return ("An unexpected error occurred. " + e.getMessage());
//            }
//        }
//        scanner.close();
//    }

}

