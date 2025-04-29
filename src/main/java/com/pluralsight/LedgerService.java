package com.pluralsight;

import java.util.List;

// LedgerService Class - Basic Methods
public class LedgerService {

    // Add a transaction to the ledger
    public void addTransaction(Transaction transaction) {
        // TODO: Write transaction to transactions.csv file
    }

    // Read all transactions from the ledger
    public List<Transaction> readAllTransactions() {
        // TODO: Read transactions.csv and return a list of Transaction objects
        return null;
    }

    // Filter transactions - Deposits only
    public List<Transaction> getDeposits(List<Transaction> transactions) {
        // TODO: Return only transactions where amount > 0
        return null;
    }

    // Filter transactions - Payments only
    public List<Transaction> getPayments(List<Transaction> transactions) {
        // TODO: Return only transactions where amount < 0
        return null;


    }
}
