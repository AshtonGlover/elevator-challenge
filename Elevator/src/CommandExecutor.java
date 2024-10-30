import java.util.Map;

/**
 * This is the CommandExecutor class. It handles executing elevator commands based on the user input
 * from the REPL.
 */
public class CommandExecutor {
    private final Elevator elevator;

    public CommandExecutor(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * This method takes in the user's command and contains a switch statement to execute different
     * commands based on the user's input.
     */
    public String executeCommand(String command) {
        String result = "";
        switch (command) {
            case "up":
                Map<String, String> aboveFloors = this.elevator.getFloors();
                String validUpper = aboveFloors.get("UP");
                if (validUpper == null) {
                    result = "You are on the fifth floor and cannot go up.";
                } else {
                    result = "You can go up to floor(s) " + validUpper + ". Select one to move to.";
                }
                break;
            case "down":
                Map<String, String> belowFloors = this.elevator.getFloors();
                String validLower = belowFloors.get("DOWN");
                if (validLower == null) {
                    result = "You are in the basement and cannot go down.";
                } else {
                    result = "You can go down to floor(s) " + validLower + ". Select one to move to.";
                }
                break;
            case "current floor":
                result = this.elevator.getCurrentFloor();
                break;
            case "b":
            case "l":
            case "2":
            case "3":
            case "4":
            case "5":
                String end = String.valueOf(command.charAt(command.length() - 1));
                this.elevator.moveElevator(command);
                result = "You have reached floor " + end.toUpperCase();
                break;
            default:
                result = "Invalid command - please refer to the options listed at the start";
        }

        return result;
    }


}