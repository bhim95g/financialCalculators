package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class LedgerService {

    public void addTransaction(Transaction transaction) {
        try (FileWriter fw = new FileWriter("transactions.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(transaction.toCSVString());
            System.out.println("Transaction saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

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

                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        Collections.reverse(transactions); // Newest first
        return transactions;
    }

    public List<Transaction> getDeposits(List<Transaction> transactions) {
        return transactions.stream().filter(t -> t.getAmount() > 0).collect(Collectors.toList());
    }

    public List<Transaction> getPayments(List<Transaction> transactions) {
        return transactions.stream().filter(t -> t.getAmount() < 0).collect(Collectors.toList());
    }

    public List<Transaction> monthToDate(List<Transaction> all) {
        LocalDate today = LocalDate.now();
        return all.stream()
                .filter(t -> t.getDate().getMonth() == today.getMonth() && t.getDate().getYear() == today.getYear())
                .collect(Collectors.toList());
    }

    public List<Transaction> previousMonth(List<Transaction> all) {
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        return all.stream()
                .filter(t -> YearMonth.from(t.getDate()).equals(lastMonth))
                .collect(Collectors.toList());
    }

    public List<Transaction> yearToDate(List<Transaction> all) {
        int currentYear = LocalDate.now().getYear();
        return all.stream()
                .filter(t -> t.getDate().getYear() == currentYear)
                .collect(Collectors.toList());
    }

    public List<Transaction> previousYear(List<Transaction> all) {
        int lastYear = LocalDate.now().getYear() - 1;
        return all.stream()
                .filter(t -> t.getDate().getYear() == lastYear)
                .collect(Collectors.toList());
    }

    public List<Transaction> searchByVendor(List<Transaction> all, String vendor) {
        return all.stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendor.trim()))
                .collect(Collectors.toList());
    }

    public void printTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
}


