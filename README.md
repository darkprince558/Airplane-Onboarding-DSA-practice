Airplane Boarding Simulator

A Java-based CLI application that simulates the logic of airplane boarding and disembarking. I built this to experiment with different linear data structures and optimize seat allocation algorithms for O(1) time complexity.

Overview

This program models the flow of passengers from a gate queue to their seats and back out again. Instead of a simple array, it uses a combination of Queues, Stacks, and Lists to handle the different flow mechanics of a real-world scenario (First-In-First-Out vs Last-In-First-Out).

Technical Implementation

The core logic handles seat management efficiently without iterating through the entire plane for every operation.

Boarding (FIFO): Uses a LinkedList as a Queue to manage the line. Passengers board in the exact order they arrive.

Seating (O(1) Access): The seat layout is an ArrayList allowing constant-time access to any specific seat index.

Seat Allocation (O(1) Optimization): I implemented a separate Queue<Integer> that pre-fills with all valid seat indices. When a passenger boards, the system simply polls the next available index. This eliminates the need to search the entire seating array for an empty spot, keeping the boarding process O(1) regardless of plane size.

Disembarking (LIFO): A Stack tracks the seating order. The simulation assumes a LIFO model (last person seated is closest to the exit), so they are popped off the stack first.

Features

Queue Management: Add specific passengers or generate random ones for stress testing.

Visual Layout: View a grid-based map of the plane to see exactly who is sitting where.

Smart Boarding: Automatically assigns the next available seat from front-to-back.

State Tracking: Handles edge cases like full planes or empty queues gracefully.

How to Run

Clone the repository.

Compile the Java files:

javac LA5Q/*.java


Run the main application:

java LA5Q.Lab5Main


Example Output

Current Seat Layout:
---------------------------------------------------------
Row 1 : [  John Doe  ] [ Jane Smith ] [   Empty    ] [   Empty    ]
Row 2 : [   Empty    ] [   Empty    ] [   Empty    ] [   Empty    ]
Row 3 : [   Empty    ] [   Empty    ] [   Empty    ] [   Empty    ]
---------------------------------------------------------


Future Improvements

Implement seat preferences (Window vs. Aisle).

Add priority boarding queues.

Visual UI using JavaFX.