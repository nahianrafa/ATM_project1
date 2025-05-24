package com.mycompany.atm;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private List<String> transactions;

    public Account(double balance) {
        this.balance = balance;
        transactions = new ArrayList<>();
        transactions.add("Account opened with ₹" + balance);
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn ₹" + amount + " | Balance: ₹" + balance);
            return true;
        }
        transactions.add("Failed withdrawal of ₹" + amount + " due to insufficient balance.");
        return false;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited ₹" + amount + " | Balance: ₹" + balance);
    }

    public void transfer(Account receiver, double amount) {
        if (withdraw(amount)) {
            receiver.deposit(amount);
            transactions.add("Transferred ₹" + amount + " to another account");
            receiver.addTransaction("Received ₹" + amount + " from another account");
        } else {
            transactions.add("Failed transfer of ₹" + amount + " due to insufficient balance.");
        }
    }

    // Helper for receiver transaction log
    private void addTransaction(String note) {
        transactions.add(note + " | Balance: ₹" + balance);
    }

    public void showTransactions() {
        System.out.println("Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactions) {
                System.out.println("- " + t);
            }
        }
    }
}