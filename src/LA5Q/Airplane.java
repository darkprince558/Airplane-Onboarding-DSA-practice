package LA5Q;

import java.util.*;

/**
 * The Airplane class is the core and holds the main logic of the simulation.
 * It manages the seating layout and all the logic for boarding and disembarking passengers using linear data structures.
 */
public class Airplane {

    /**
     * RATIONALE for boardingQueue:
     * A Queue is used to manage passengers waiting to board.
     * This is the perfect data structure because it follows the First-In, First-Out (FIFO)
     * principle. This perfectly models a real-world line: the first passenger to join the queue is the first one to board the plane.
     * We use LinkedList as the concrete implementation, as it's efficient for Queue operations (O(1) time operations).
     */
    private Queue<String> boardingQueue;

    /**
     * RATIONALE for seatLayout:
     * A List (specifically an ArrayList) represents the airplane's seating.
     * A List is chosen because its key feature is O(1) element access.
     * This allows us to instantly get() or set() the status of any seat using its index, which is ideal for a seating chart.
     * We can easily find the 2D (row, seat) position from a 1D index:
     *   row = index / seatsPerRow
     *   seat = index % seatsPerRow
     */
    private List<String> seatLayout;

    /**
     * RATIONALE for disembarkStack:
     * A Stack is used to manage the disembarking order.
     * A Stack follows the Last-In First-Out (LIFO) principle.
     * This means the last passenger who boarded will be the first one to disembark.
     * +++++Had to choose between Stack<Integer> vs. Stack<String>++++
     * - We store the seat INDEX (as an Integer) on the stack and not the passenger's name (as a String).
     * - When disembarking, we pop() the index and can immediately clear that seat in O(1) time.
     * - If we had stored names, we would have to search the entire seatLayout (an O(n) operation) and the logic would fail if two passengers had the same name.
     */
    private Stack<Integer> disembarkStack;

    /**
     * RATIONALE for availableSeats:
     * This second Queue is used to track all empty seats.
     * Instead of searching the seatLayout for an empty seat (an O(n) operation) every time we board, we can:
     * 1. poll() an empty seat index from this queue in O(1) time
     * 2. add() an index back to this queue in O(1) time when disembarking
     * This also provides an O(1) method to check if the plane is full by simply calling availableSeats.isEmpty().
     */
    private Queue<Integer> availableSeats;

    // Instance variables for the plane's dimensions
    private final int rows;
    private final int seatsPerRow;

    /**
     * Constructor to initialize the Airplane.
     * @param rows The number of rows in the airplane.
     * @param seatsPerRow The number of seats in each row.
     */
    public Airplane(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        int totalSeats = rows * seatsPerRow;

        // Initialize the data structures
        this.boardingQueue = new LinkedList<>();
        this.disembarkStack = new Stack<>();
        // fill the seatLayout with "Empty" strings
        this.seatLayout = new ArrayList<>(Collections.nCopies(totalSeats, "Empty"));

        // pre-fill the availableSeats queue with all possible indices (0 to totalSeats-1)
        this.availableSeats = new LinkedList<>();
        for (int i = 0; i < totalSeats; i++) this.availableSeats.add(i);
    }

    /**
     * Adds a passenger to the end of the boarding queue.
     * @param passengerName The name of the passenger to add.
     */
    public void addPassengerToQueue(String passengerName) {
        // Simple check to prevent adding blank names
        if (passengerName == null || passengerName.trim().isEmpty()) {
            System.out.println("Passenger name cannot be empty.");
            return;
        }
        // The queue.add() method adds the passenger to the end of the line (FIFO).
        boardingQueue.add(passengerName);
        // Print a success message
        System.out.println(passengerName + " has been added to the boarding queue.");
    }

    /**
     * Boards the next passenger from the queue.
     */
    public void boardPassenger() {
        // Check if the queue is empty (O(1) check).
        if (boardingQueue.isEmpty()) {
            System.out.println("The boarding queue is empty. No passengers to board.");
            return;
        }

        // Check if the plane is full using our availableSeats queue (O(1) check).
        if (availableSeats.isEmpty()) {
            // Plane is full.
            // Peek() to see who is at the front of the queue.
            System.out.println("The airplane is full. " + boardingQueue.peek() + " could not be seated at this time.");
            return;
        }

        // Seat available
        // poll() removes the passenger from the front of the boarding queue, and the next available seat index
        String passenger = boardingQueue.poll();
        int seatToFill = availableSeats.poll();
        // update the seatLayout and add the seat index to the stack
        seatLayout.set(seatToFill, passenger);
        disembarkStack.push(seatToFill);

        int row = seatToFill / seatsPerRow + 1;
        int seat = seatToFill % seatsPerRow + 1;
        System.out.println(passenger + " has been seated at Row " + row + " Seat " + seat);
    }

    /**
     * Displays the current seat layout in a user-friendly grid format.
     */
    public void displaySeatLayout() {
        System.out.println("\nCurrent Airplane Seat Layout:");
        System.out.println("---------------------------------------------------------");

        for (int r = 0; r < rows; r++) {
            // Print the row number, left-aligned
            System.out.printf("Row%-2d: ", (r + 1));

            for (int s = 0; s < seatsPerRow; s++) {
                // Calculate the 1D index from the 2D (r, s) coordinates
                int index = r * seatsPerRow + s;
                String seatedPassenger = seatLayout.get(index);
                // Print the seated passenger in each seat, formatted to 12 chars, prints empty seats as "Empty"
                System.out.printf("[%12.12s] ", seatedPassenger);
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Disembarks the last passenger who boarded (LIFO).
     * This is an efficient O(1) operation.
     */
    public void disembarkPassenger() {
        if (disembarkStack.isEmpty()) {
            System.out.println("The airplane is empty. All passengers have disembarked.");
            return;
        }
        // Pop the seat INDEX from the top of the stack.
        int seatIndex = disembarkStack.pop();
        // Get the passenger's name from that index for the printed message.
        String passenger = seatLayout.get(seatIndex);
        seatLayout.set(seatIndex, "Empty");
        // Add the now-empty seat index back to the pool of available seats.
        availableSeats.add(seatIndex);

        int row = seatIndex / seatsPerRow + 1;
        int seat = seatIndex % seatsPerRow + 1;
        System.out.println(passenger + " (from Row " + row + " Seat " + seat + ") has disembarked.");
    }
}