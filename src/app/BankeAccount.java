package app;

public class BankeAccount {
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String pin;
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

    public double getBalance(double amount) {
        if (amount <= 0) {
            return 0;
        }

        balance += amount;
        return balance;
    }

    public boolean deposit(double amount) {
        return getBalance(amount) > 0;
    }
}
