import java.util.Scanner;

public class Main {

    /**
     * This is the main line. It creates a REPL for user input regarding the elevator. There are a list of
     * valid commands that get printed at the beginning.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Elevator elevator = new Elevator();
        CommandExecutor commandExecutor = new CommandExecutor(elevator);

        System.out.println("Welcome to the elevator! Here are your list of possible commands:");
        System.out.println("'Current Floor' - this will tell you the floor that the elevator is currently on.");
        System.out.println("'Up' - this will tell you the valid floors that you can move up to.");
        System.out.println("'Down' - this will tell you the valid floors that you can move down to.");
        System.out.println("Typing in the name of a floor (B, L, 2, 3, 4, or 5) will take you to that floor" +
                " if it is valid.");
        System.out.println("'Exit' - to leave the building and the elevator system (close the REPL)");
        System.out.println(" ");
        System.out.println("To get started, type your command below:");

        while (true) {
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Exiting...");
                break;
            }

            String result = commandExecutor.executeCommand(input);
            System.out.println(result);
        }

        scanner.close();
    }
}