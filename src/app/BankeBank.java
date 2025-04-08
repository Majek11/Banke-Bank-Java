package app;

import java.util.HashMap;
import java.util.Scanner;

public class BankeBank {
    private HashMap<String, BankeAccount> bankeBankAccounts = new HashMap<>();
    private long nextAccountNumberSequence = 6875632;

 public String createAccount(String firstName, String lastName, String pin) {
     if (!checkUserInputs(firstName, lastName, pin)) {
         return null;
     }

     String accountNumber = "300" + String.format("%7d", nextAccountNumberSequence);
     nextAccountNumberSequence++;
     bankeBankAccounts.put(accountNumber, new BankeAccount(accountNumber, firstName, lastName, pin, 0));
     return accountNumber;
 }

    private boolean checkUserInputs(String firstName, String lastName, String pin) {
        if (firstName == null || lastName == null) return false;
        if (firstName.isEmpty() || lastName.isEmpty()) return false;
        if (pin == null || pin.isEmpty()) return false;
        try {
            Integer.parseInt(pin);
        } catch (NumberFormatException e) {
            return false;
        }
        return pin.length() == 4;
    }

    public boolean deposit(String accountNumber, String pin, double amount) {
        BankeAccount account = bankeBankAccounts.get(accountNumber);
        if ( account == null || !account.getPin().equals(pin) || amount <= 0 ) {
            return false;
        }
        return account.deposit(amount);
    }

    public boolean withdraw(String accountNumber, String pin, double amount) {
        BankeAccount account = bankeBankAccounts.get(accountNumber);
        if (account == null || !account.getPin().equals(pin) || amount <= 0 || account.getBalance() < amount) {
            return false;
        }
        return account.withdraw(amount);
    }

    public boolean closeAccount(String accountNumber, String pin) {
        BankeAccount account = bankeBankAccounts.get(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            bankeBankAccounts.remove(accountNumber);
            return true;
        }
        return false;
    }

    public double getBalance(String accountNumber, String pin) {
        BankeAccount account = bankeBankAccounts.get(accountNumber);
        if (account == null || !account.getPin().equals(pin)) {
            throw new IllegalArgumentException("Invalid account number or pin");
        }
        return account.getBalance();
    }

    public static void main(String[] args) {
        BankeBank bank = new BankeBank();
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to Banke Bank ATM!");

        do {
            System.out.println("\nOptions:");
            System.out.println("1. Create an account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Close account");
            System.out.println("5. Check balance");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter a 4-digit PIN: ");
                    String pin = scanner.nextLine();
                    String accountNumber = bank.createAccount(firstName, lastName, pin);
                    if (accountNumber != null) {
                        System.out.println("Account created! Your account number is: " + accountNumber);
                    } else {
                        System.out.println("Failed to create account. Check your inputs.");
                    }
                    break;

                case "2":
                    System.out.print("Enter account number: ");
                    String depositAccount = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String depositPin = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    if (bank.deposit(depositAccount, depositPin, depositAmount)) {
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Deposit failed. Check account number, PIN, or amount.");
                    }
                    break;

                case "3":
                    System.out.print("Enter account number: ");
                    String withdrawAccount = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String withdrawPin = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (bank.withdraw(withdrawAccount, withdrawPin, withdrawAmount)) {
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Withdrawal failed. Check account number, PIN, or balance.");
                    }
                    break;

                case "4":
                    System.out.print("Enter account number: ");
                    String closeAccount = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String closePin = scanner.nextLine();
                    if (bank.closeAccount(closeAccount, closePin)) {
                        System.out.println("Account closed successfully!");
                    } else {
                        System.out.println("Failed to close account. Check account number or PIN.");
                    }
                    break;

                case "5":
                    System.out.print("Enter account number: ");
                    String balanceAccount = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String balancePin = scanner.nextLine();
                    try {
                        double balance = bank.getBalance(balanceAccount, balancePin);
                        System.out.println("Your balance is: " + balance);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to check balance. Check account number or PIN.");
                    }
                    break;

                case "6":
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-6.");
            }
        } while (!choice.equals("6"));
    }

}
