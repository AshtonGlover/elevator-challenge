# elevator-challenge

This is my code simulating an elevator. I decided to implement my elevator in a REPL format, where the user can constantly input different commands to either move up or down in the building, display the floor they are currently on, or list and floors that they are able to move up or down to. This was broken down into three main classes:

1. Main - this handles the REPL for user input
2. CommandExecutor - this executes any commands that the user inputs
3. Elevator - this handles all of the Elevator logic

There were a few assumptions made during the implementation. For one, this code handles a single elevator system for a building with floors B, L, 2, 3, 4, and 5. Furthermore, this code does not account for multiple people trying to use the elevator at the same time. It only handles the movement of a single person through the building. In addition, the elevator does not reset to the lobby. It simply goes to the requested floor and waits there until the person decides to move floors. Lastly, you cannot stop the elevator while it is moving. If it is going to a specific floor, it will reach that floor and does not handle intermediate stops. 
