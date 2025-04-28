package com.pluralsight;
import java.util.Scanner;

public class LedgerApp {

    public static void main(String[] args) {
        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main application loop
        while (running) {
            // Home Screen Menu
            System.out.println("======================================");
            System.out.println("Welcome to the Accounting Ledger App!");
            System.out.println("======================================");
            System.out.println("Please choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    // Add Deposit Flow
                    System.out.println("// TODO: Handle Add Deposit");
                    break;
                case "P":
                    // Make Payment Flow
                    System.out.println("// TODO: Handle Make Payment");
                    break;
                case "L":
                    // Ledger Screen Flow
                    System.out.println("// TODO: Handle Ledger Display");
                    break;
                case "X":
                    // Exit Application
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Close Scanner before exiting
        scanner.close();
    }
}
