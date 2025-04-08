package app;

import java.util.HashMap;
import java.util.Scanner;

public class BankeBank {
    private HashMap<String, BankeAccount> bankeBankAccounts = new HashMap<>();
    private int accountCount = 1001;

    public String createAccount(String firstName, String lastName, String pin) {
        if (!checkUserInputs(firstName, lastName, pin)) return null;
        String accountNumber = String.valueOf(accountCount);
        accountCount = accountCount + 1;
        bankeBankAccounts.put(accountNumber, new BankeAccount(accountNumber, firstName, lastName, pin, 0.0));
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

    public static void main(String[] args) {

    }
}
