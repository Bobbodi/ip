public class Bobbodi {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }

    public final static String dashes = "____________________________________________________________";
    public final static String chatbotName = "Bobbodi";

    public static void greeting() {
        System.out.println(dashes);
        System.out.println("Hello! I'm "+chatbotName);
        System.out.println("What can I do for you?");
        System.out.println(dashes);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(dashes);
    }

}
