package com.mycompany.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
    private static Map<Integer, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to ATM System");

        // Register user
        System.out.print("Create your User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Create your Password (must include @, #, $, %): ");
        String password = scanner.nextLine();

        while (!password.matches(".*[@#$%].*")) {
            System.out.println("Invalid password. Must contain at least one special character (@, #, $, %).");
            System.out.print("Enter password again: ");
            password = scanner.nextLine();
        }

        System.out.print("Enter initial balance: ₹");
        double balance = scanner.nextDouble();

        users.put(userId, new User(userId, password, balance));
        System.out.println("User registered successfully!\n");

        // Login section
        System.out.print("Enter User ID to login: ");
        int loginId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Password: ");
        String loginPassword = scanner.nextLine();

        User user = users.get(loginId);

        if (user != null && user.authenticate(loginPassword)) {
            System.out.println("Login successful!");
            runATM(user);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public static void runATM(User user) {
        int choice;
        do {
            System.out.println("\n1. Balance Inquiry\n2. Withdraw\n3. Transfer\n4. View Transactions\n5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            Account account = user.getAccount();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: ₹" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double amount = scanner.nextDouble();
                    if (account.withdraw(amount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 3:
                    System.out.print("Enter receiver User ID: ");
                    int receiverId = scanner.nextInt();
                    System.out.print("Enter amount to transfer: ₹");
                    double transferAmt = scanner.nextDouble();
                    User receiver = users.get(receiverId);
                    if (receiver != null) {
                        account.transfer(receiver.getAccount(), transferAmt);
                        System.out.println("Transfer attempted.");
                    } else {
                        System.out.println("Receiver not found.");
                    }
                    break;
                case 4:
                    account.showTransactions();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM System.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }
}