package app;

public class BankeAccount {
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String pin;
    private double balance;


    public BankeAccount( String accountNumber, String firstName, String lastName, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.balance = balance;
    }
}
