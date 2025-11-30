# Airplane Boarding System

A simple Java command-line simulation that demonstrates how different data structures manage passenger flow.

## What it does

This program simulates the process of passengers waiting in line, finding their seats, and disembarking. It's designed to show the difference between **FIFO** (First-In-First-Out) and **LIFO** (Last-In-First-Out) logic in a practical scenario.

## How it works

The core logic uses standard Java data structures to handle the simulation efficiently:

* **Boarding Queue:** Uses a `Queue` to manage the line. Passengers board in the exact order they arrive (FIFO).
* **Seating Chart:** Uses an `ArrayList` to represent the seats, allowing the system to instantly update any seat status.
* **Disembarking:** Uses a `Stack` to track who sat down last. This simulates a LIFO flow where the last person seated (closest to the aisle/door) leaves first.
* **Efficiency:** Instead of searching for empty seats every time, the system tracks available spots in a separate pool, making the boarding process instant.

## Features

* **Visual Seat Map:** See a grid layout of who is sitting where.
* **Queue Management:** Add passengers manually or generate random names for testing.
* **Smart Boarding:** Automatically fills the plane from front to back.
* **Status Handling:** Correctly handles full flights and empty queues.

## How to Run

1.  Clone the repository.
2.  Compile the files:
    ```bash
    javac AirplaneImplementation/*.java
    ```
3.  Run the program:
    ```bash
    java AirplaneImplementation.Main
    ```

## Example Output

```text
Current Seat Layout:
---------------------------------------------------------
Row 1 : [  John Doe  ] [ Jane Smith ] [   Empty    ] [   Empty    ] 
Row 2 : [   Empty    ] [   Empty    ] [   Empty    ] [   Empty    ] 
Row 3 : [   Empty    ] [   Empty    ] [   Empty    ] [   Empty    ] 
---------------------------------------------------------