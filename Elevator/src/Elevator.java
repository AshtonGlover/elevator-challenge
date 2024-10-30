import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Simple direction enum to keep track of which direction the elevator is moving
 */
enum Direction {
    UP(1),
    DOWN(-1),
    STILL(0);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

/**
 * This is the Elevator class. It holds instance variables which track the current floor and
 * direction, and houses necessary methods to move the elevator.
 */
public class Elevator {
    private String floor;
    private Direction direction;

    public Elevator() {
        this.floor = "L";
        this.direction = Direction.STILL;
    }

    /**
     * Method to get the valid floors both above and below the current floor.
     */
    public Map<String, String> getFloors() {
        Map<String, String> cmdToFloors = new HashMap<>();
        switch (this.floor) {
            case "B":
                cmdToFloors.put("UP", "L, 2, 3, 4, 5");
                break;
            case "L":
                cmdToFloors.put("DOWN", "B");
                cmdToFloors.put("UP", "2, 3, 4, 5");
                break;
            case "2":
                cmdToFloors.put("DOWN", "B, L");
                cmdToFloors.put("UP", "3, 4, 5");
                break;
            case "3":
                cmdToFloors.put("DOWN", "B, L, 2");
                cmdToFloors.put("UP", "4, 5");
                break;
            case "4":
                cmdToFloors.put("DOWN", "B, L, 2, 3");
                cmdToFloors.put("UP", "5");
                break;
            case "5":
                cmdToFloors.put("DOWN", "B, L, 2, 3, 4");
                break;
            default:
                break;
        }

        return cmdToFloors;
    }

    /**
     * Method to retrieve the current floor
     */
    public String getCurrentFloor() {
        return this.floor;
    }

    /**
     * Method to set the current floor
     */
    public void setCurrentFloor(String newFloor) {
        this.floor = newFloor.toUpperCase();
    }

    /**
     * Method to convert a floor value to a string (to handle basement and lobby)
     */
    public int floorToNum(String floor) {
        if (floor.equalsIgnoreCase("b")) {
            return 0;
        } else if (floor.equalsIgnoreCase("l")) {
            return 1;
        }

        return Integer.parseInt(floor);
    }

    /**
     * Method to convert a numbered floor to a string
     */
    public String numToFloor(int floor) {
        if (floor == 0) {
            return "B";
        } else if (floor == 1) {
            return "L";
        }
        return Integer.toString(floor);
    }

    /**
     * Method to change the direction that the elevator is moving in
     */
    public int setDirection(int start, int end) {
        if (start > end) {
            this.direction = Direction.DOWN;
        } else if (start < end) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.STILL;
        }

        return this.direction.getValue();
    }

    /**
     * Method to move the elevator to a new floor. Takes two seconds for the elevator to get through each floor,
     * printing as it moves.
     */
    public void moveElevator(String input) {
        int[] count = new int[]{0};
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        int startFloor = this.floorToNum(this.getCurrentFloor());
        String end = String.valueOf(input.charAt(input.length() - 1));

        int endFloor = this.floorToNum(end);
        int timesToExecute = Math.abs(endFloor - startFloor);
        int dir = this.setDirection(startFloor, endFloor);
        executorService.scheduleAtFixedRate(() -> {
            if (timesToExecute == 0) {
                System.out.println("You are already at floor " + end.toUpperCase());
                executorService.shutdown();
            } else if (count[0] < timesToExecute) {
                int passingFloor = startFloor + (dir * count[0]);
                if (count[0] == 0) {
                    System.out.println("Leaving floor " + this.numToFloor(passingFloor));
                } else {
                    System.out.println("Passing floor " + this.numToFloor(passingFloor));
                }
                count[0]++;
            } else {
                this.setCurrentFloor(end);
                executorService.shutdown();
            }
        }, 2, 2, TimeUnit.SECONDS);

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}