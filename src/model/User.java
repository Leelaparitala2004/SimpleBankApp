package model;

public class User {
    private String username;
    private String password;
    private BankAccount account;

    public User(String username, String password, String accountNumber) {
        this.username = username;
        this.password = password;
        this.account = new BankAccount(accountNumber);
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public BankAccount getAccount() { return account; }

    public String toDataString() {
        return username + "," + password + "," + account.getAccountNumber() + "," + account.getBalance();
    }
}
