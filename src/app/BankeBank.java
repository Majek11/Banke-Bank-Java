package app;

import java.util.HashMap;


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
        if (account == null || !account.getPin().equals(pin) || amount <= 0) {
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

    public boolean transfer(String fromAccount, String pin, String toAccount, double amount) {
        BankeAccount source = bankeBankAccounts.get(fromAccount);
        BankeAccount destination = bankeBankAccounts.get(toAccount);
        if ( source == null || !source.getPin().equals(pin) || destination == null || amount <= 0 || source.getBalance() < amount) {
            return false;
        }
        source.withdraw(amount);
        destination.deposit(amount);
        return true;
    }
}
