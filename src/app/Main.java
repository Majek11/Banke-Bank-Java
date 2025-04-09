package app;
import java.lang.IllegalArgumentException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BankeBank bank = new BankeBank();
        Scanner userInputCollector = new Scanner(System.in);
        String accountNumber = null;
        String pin = null;
        String fullName = null;
        String choice;

        System.out.println("Welcome to Banke Bank ATM! ");

        while (accountNumber == null) {
            System.out.print("Enter your account number (or type 'new' to create a new account)");
            String input = userInputCollector.nextLine();

            if (input.equalsIgnoreCase("new")) {
                System.out.print("Enter your first name: ");
                String firstName = userInputCollector.nextLine();
                System.out.print("Enter your last name: ");
                String lastName = userInputCollector.nextLine();
                System.out.print("Enter a 4-digit PIN: ");
                pin = userInputCollector.nextLine();
                accountNumber = bank.createAccount(firstName, lastName, pin);

                if (accountNumber != null) {
                    fullName = firstName + " " + lastName;
                    System.out.println("Account created! Your account number is: " + accountNumber);
                    System.out.println();
                    System.out.println("Welcome to Banke Bank " + fullName);
                } else {
                    System.out.println("Failed to create account. Check your inputs (names must not be empty, PIN must be 4 digits).");
                }
            } else{
                System.out.println("Enter your account PIN");
                pin = userInputCollector.nextLine();
                try {
                    bank.getBalance(input, pin);
                    accountNumber = input;
                    double balance = bank.getBalance(input, pin);
                    accountNumber = input;
                    System.out.println("Logged in successfully!");
                    System.out.println("You are logged in as " + null);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid account number or PIN. Please try again.");
                    accountNumber = null;
                }
            }
        }
        do {
            System.out.println("\nOptions:");
            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Check balance");
            System.out.println("4. Close account");
            System.out.println("5. Transfer money");
            System.out.println("6. Change PIN");
            System.out.println("7. Log out");
            System.out.print("Enter your choice (1-7): ");
            choice = userInputCollector.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(userInputCollector.nextLine());
                    if (bank.deposit(accountNumber, pin, depositAmount)) {
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Deposit failed. Amount must be positive.");
                    }
                    break;

                case "2":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(userInputCollector.nextLine());
                    if (bank.withdraw(accountNumber, pin, withdrawAmount)) {
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Withdrawal failed. Check balance or amount.");
                    }
                    break;

                case "3":
                    double balance = bank.getBalance(accountNumber, pin);
                    System.out.println("Your balance is: " + balance);
                    break;

                case "4":
                    if (bank.closeAccount(accountNumber, pin)) {
                        System.out.println("Account closed successfully! Logging out...");
                        accountNumber = null;
                        choice = "7";
                    } else {
                        System.out.println("Failed to close account.");
                    }
                    break;

                case "5":
                    System.out.print("Enter destination account number: ");
                    String toAccount = userInputCollector.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = Double.parseDouble(userInputCollector.nextLine());
                    if (bank.transfer(accountNumber, pin, toAccount, transferAmount)) {
                        System.out.println("Transfer successful!");
                    } else {
                        System.out.println("Transfer failed. Check balance, amount, or destination account.");
                    }
                    break;

                case "6":
                    System.out.print("Enter current PIN: ");
                    String oldPin = userInputCollector.nextLine();
                    System.out.print("Enter new 4-digit PIN: ");
                    String newPin = userInputCollector.nextLine();
                    if (bank.changePin(accountNumber, oldPin, newPin)) {
                        pin = newPin;
                        System.out.println("PIN changed successfully!");
                    } else {
                        System.out.println("PIN change failed. Check current PIN or ensure new PIN is 4 digits.");
                    }
                    break;

                case "7":
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-7.");
            }
        } while (!choice.equals("7"));

        System.out.println("Thank you for banking with us!");

}
}
