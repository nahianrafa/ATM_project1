package com.mycompany.atm;

public class User {
    private int userId;
    private String password;
    private Account account;

    public User(int userId, String password, double balance) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password must contain at least one special character (@, #, $, %)");
        }
        this.userId = userId;
        this.password = password;
        this.account = new Account(balance);
    }

    private boolean isValidPassword(String password) {
        return password.matches(".*[@#$%].*");
    }
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public Account getAccount() {
        return account;
    }

    public int getUserId() {
        return userId;
    }
}