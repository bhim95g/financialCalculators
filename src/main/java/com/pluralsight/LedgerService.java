package com.pluralsight;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Collections;
import java.time.YearMonth;
import java.util.stream.Collectors;

// LedgerService Class - Basic Methods
public class LedgerService {

    // Add a transaction to the ledger
    public void addTransaction(Transaction transaction) {
        try (FileWriter fw = new FileWriter("transactions.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(transaction.toCSV());
            System.out.println("Transaction saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Reading Transaction
    public List<Transaction> readAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        // Reverse list for newest-first order
        Collections.reverse(transactions);
        return transactions;
    }


    // Deposits
    public List<Transaction> getDeposits(List<Transaction> transactions) {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }
        return deposits;


        // Payments
        public List<Transaction> getPayments(List<Transaction> transactions) {
            List<Transaction> payments = new ArrayList<>();
            for (Transaction t : transactions) {
                if (t.getAmount() < 0) {
                    payments.add(t);
                }
            }
            return payments;
        }
    }
}


