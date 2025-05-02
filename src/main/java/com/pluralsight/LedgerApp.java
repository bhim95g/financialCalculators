package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class LedgerApp {

    public static void main(String[] args) {
        // Create a Scanner to read user input from the command line
        Scanner scanner = new Scanner(System.in);

        final LedgerService ledgerService = new LedgerService();
        // This Controls the main application loop
        boolean running = true;

        // Main menu
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

            //Converting user input to upper case
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    // Handle Add Deposit
                    Transaction deposit = promptForTransaction(scanner, true);
                    break;

                case "P":
                    // Handle Make Payment
                    Transaction payment = promptForTransaction(scanner, false);
                    break;

                case "L":
                    //Ledger Screen
                    ArrayList<Transaction> transactions = new ArrayList<>();
                    ledgerScreen(scanner, transactions);
                    break;

                case "X":
                    // Exit the application
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
// Close the scanner to free system resources
        scanner.close();
    }

    // User Deposit
    //Allows user to enter the details of a transaction. If deposit the amount stays positive. If payment amount is turned negative.

    private static Transaction promptForTransaction(Scanner scanner, boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Convert payment amount to negative
        if (!isDeposit) {
            amount *= -1;  // Make it negative for payment
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        return new Transaction(date, time, description, vendor, amount);
    }
// Ledger Screen
    private static void ledgerScreen(Scanner scanner, ArrayList<Transaction> transactions) {
        boolean running = true;
        while (running) {
            System.out.println("Please select menu options below");
            System.out.println("(A) View All Transaction");
            System.out.println("(D) View All Deposits");
            System.out.println("(P) View All Payments");
            System.out.println("(H) Home (Return to Main Manu)");
            System.out.println("Enter your choice here: ");
            String ledgerChoice = scanner.nextLine().trim().toUpperCase();

            switch (ledgerChoice) {
                case "A":
                    System.out.println("Show All Transaction");
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;

                case "D":
                    System.out.println("Show Deposits Only");
                    for (Transaction t : transactions) {
                        if (t.getAmount() > 0) {
                            System.out.println(t);
                        }
                    }
                    break;

                case "P":
                    System.out.println("Show Payments Only");
                    for (Transaction t : transactions) {
                        if (t.getAmount() < 0) {
                            System.out.println(t);
                        }
                    }
                    break;

                case "R":
                    boolean viewingReports = true;
                    while (true) {
                        System.out.println("\nReports Menu:");
                        System.out.println("1) Month To Date");
                        System.out.println("2) Previous Month");
                        System.out.println("3) Year To Date");
                        System.out.println("4) Previous Year");
                        System.out.println("5) Search by Vendor");
                        System.out.println("0) Back");
                        System.out.print("Your choice: ");
                        String reportChoice = scanner.nextLine();

                        LocalDate today = LocalDate.now();
                    }


                case "H":
                    boolean viewing = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
