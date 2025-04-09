package app;

public class BankeAccount {
    private String accountNumber;
    private String firstName;
    private String lastName;
    String pin;
    private double balance;


    public BankeAccount(String accountNumber, String firstName, String lastName, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
       return balance;
    }

    public boolean deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                return true;
            }
            return false;
    }

    public boolean withdraw(double amount) {
        if (amount >  0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
