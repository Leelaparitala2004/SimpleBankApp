package model;

public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (amount > balance) throw new Exception("Insufficient funds.");
        balance -= amount;
    }

    public void transfer(BankAccount toAccount, double amount) throws Exception {
        this.withdraw(amount);
        toAccount.deposit(amount);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
