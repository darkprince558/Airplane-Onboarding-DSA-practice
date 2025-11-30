package AirplaneImplementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

/**
 * Main class for the Airplane Boarding and Disembarking application.
 * Class handles the main user menu and user input.
 *
 * @author Anish Jami
 */
public class Main {
    // Random names for generating random passenger names to use for testing
    private static final String[] FIRST_NAMES = {
            "Anish", "Quazi", "Saad", "Emily", "David", "Amulya", "Joanna", "Kavya",
            "Shoumik", "Isabella", "James", "Mia", "Benjamin", "Charlotte", "Idel", "Grant"
    };
    private static final String[] LAST_NAMES = {
            "Jami", "Johnson", "Williams", "Khanal", "Jones", "Garcia", "Luke", "Davis",
            "Rahman", "Shepard", "Hernandez", "Gaikwad", "Gonzalez", "Wilson", "Anderson"
    };

    /**
     * The main entry point for the application.
     */
    public static void main(String[] args) {
        header();

        Scanner input = new Scanner(System.in);
        Random random = new Random(); // For creating random passengers

        // Initialize the airplane with 3 rows and 4 seats per row
        // Can be changed easily
        Airplane airplane = new Airplane(3, 4);

        int choice; //to store the user's menu choice

        // Main application loop, Keeps running until the user chooses to exit
        do {
            displayMenu(); // Show the menu options

            try {
                // Get the user's choice
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1: // Add a single passenger
                        System.out.print("Enter passenger name: ");
                        String passengerName = input.nextLine().strip();
                        airplane.addPassengerToQueue(passengerName);
                        break;
                    case 2: // Board the next passenger
                        airplane.boardPassenger();
                        break;
                    case 3: // Disembark the last passenger
                        airplane.disembarkPassenger();
                        break;
                    case 4: // Show the airplane seat map
                        airplane.displaySeatLayout();
                        break;
                    case 5: // create and add a batch of random passengers (for testing)
                        System.out.print("How many passengers should be created: ");
                        int count = input.nextInt();
                        input.nextLine();

                        for (int i = 0; i < count; i++) {
                            // Build a random name
                            String fName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                            String lName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                            airplane.addPassengerToQueue(fName + " " + lName);
                        }
                        System.out.println("\nSuccessfully added " + count + " random passengers to the queue.");
                        break;
                    case 6: // Exit
                        System.out.println("===================================================");
                        System.out.println("Exiting application. Goodbye!");
                        System.out.println("===================================================");
                        break;
                    default:
                        // Catch any numbers that aren't 1-6
                        System.out.println("===================================================");
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                        System.out.println("===================================================");
                }
            } catch (InputMismatchException e) {
                //catch non-numeric input
                System.out.println("===================================================");
                System.out.println("Invalid input. Please enter a number.");
                System.out.println("===================================================");
                input.nextLine();
                choice = 0; //reset choice to 0 to prevent infinite loop
            }

            System.out.println();

        } while (choice != 6); // Loop ends when user picks 6

        footer(); // Print the closing footer
        input.close(); // Close the scanner
    }

    /**
     * Displays the main menu to the user.
     */
    private static void displayMenu() {
        System.out.println("===================================================");
        System.out.println("     Airplane Boarding and Disembarking Menu");
        System.out.println("===================================================");
        System.out.println("1. Add Passenger to Boarding Queue");
        System.out.println("2. Board Passenger");
        System.out.println("3. Disembark Passenger");
        System.out.println("4. Display Current Seat Layout");
        System.out.println("5. Create Passengers");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }

    /**
     * Static header method as required by the lab instructions.
     */
    public static void header() {
        System.out.println("=======================================================");
        System.out.println("Lab Exercise 5: Airplane Boarding Application");
        System.out.println("Prepared by: Anish Jami");
        System.out.println("Student Number: 251419182");
        System.out.println("Goal: To simulate airplane boarding and disembarking using linear data structures.");
        System.out.println("=======================================================\n");
    }

    /**
     * Prints a standardized footer for the lab exercise.
     **/
    public static void footer() {
        System.out.println("=======================================================");
        System.out.printf("Completion of Lab Exercise 5 - is successful!%n");
        System.out.println("Signing off - Anish!");
        System.out.println("=======================================================");
    }
}