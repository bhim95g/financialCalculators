package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// LedgerService Class - Basic Methods
public class LedgerService {

    // Add a transaction to the ledger
    public void addTransaction(Transaction transaction) {
        try (FileWriter fw = new FileWriter("transactions.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(transaction.toCSVString());
            System.out.println("Transaction saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Placeholder for reading transactions
    public List<Transaction> readAllTransactions() {
        // TODO: Read transactions.csv and return list of Transaction objects
        return null;
    }

    // Placeholder for filtering deposits
    public List<Transaction> getDeposits(List<Transaction> transactions) {
        return null;
    }

    // Placeholder for filtering payments
    public List<Transaction> getPayments(List<Transaction> transactions) {
        return null;
    }
}
