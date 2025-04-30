package com.pluralsight;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class LedgerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LedgerService ledgerService = new LedgerService();
        boolean running = true;

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
                    // Handle Add Deposit
                    Transaction deposit = promptForTransaction(scanner, true);
                    ledgerService.addTransaction(deposit);
                    break;

                case "P":
                    // Handle Make Payment
                    Transaction payment = promptForTransaction(scanner, false);
                    ledgerService.addTransaction(payment);
                    break;

                case "L":
                    // TODO: Handle Ledger Display
                    System.out.println("// TODO: Handle Ledger Display");
                    break;

                case "X":
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Prompt the user for transaction input
    private static Transaction promptForTransaction(Scanner scanner, boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (!isDeposit) {
            amount *= -1;  // Make it negative for payment
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        return new Transaction(date, time, description, vendor, amount);
    }
}
