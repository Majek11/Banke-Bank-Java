package test;

import app.BankeBank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBankeBank {

    @Test
    @DisplayName("Test to check successful account creation")
    public void testCreateAccount() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        assertTrue(accountNumber.matches("\\d+"), "Account number should not be  numeric");
    }

    @Test
    @DisplayName("Test to check if first name is empty")
    public void testCreateAccountWithEmptyFirstName() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("", "Doe", "1234");
        assertNull(accountNumber, "Account should return null if first name is empty");
    }

    @Test
    @DisplayName("Test to check if last name is empty")
    public void testCreateAccountWithEmptyLastName() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "", "1234");
        assertNull(accountNumber, "Account should return null if last name is empty");
    }

    @Test
    @DisplayName("Test to check if pin is not 4 digits")
    public void testPinNotFourDigits() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "123");
        assertNull(accountNumber, "Account should return null if pin is not 4 digits");
    }

    @Test
    @DisplayName("Test to check if pin is empty")
    public void testPinEmpty() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "");
        assertNull(accountNumber, "Account should return null if pin is empty");
    }

    @Test
    @DisplayName("Test to check if pin is not numeric")
    public void testPinNotNumeric() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "12abc");
        assertNull(accountNumber, "Account should return null if pin is not numeric");
    }

    @Test
    @DisplayName("Test to check if user can deposit money")
    public void testDepositMoney() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit(accountNumber, "1234", 100.0);
        assertTrue(result,"Deposit should be be successful");
    }

    @Test
    @DisplayName("Test to check if user can not deposit negative money")
    public void testDepositNegativeMoney() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit(accountNumber, "1234", -100.0);
        assertFalse(result,"Deposit should not be be successful");
    }

    @Test
    @DisplayName("Test to check if user can not deposit zero money")
    public void testDepositZeroMoney() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit(accountNumber, "1234", 0.0);
        assertFalse(result,"Deposit should not be be successful");
    }

    @Test
    @DisplayName("Test to check if user can not deposit money to non existing account")
    public void testDepositToNonExistingAccount() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit("invalidAccount", "1234", 100.0);
        assertFalse(result,"Deposit should not be be successful");
    }

    @Test
    @DisplayName("Test to check if user can withdraw money")
    public void testWithdrawMoney() {
      BankeBank bank = new BankeBank();
      String accountNumber = bank.createAccount("John", "Doe", "1234");
      bank.deposit(accountNumber, "1234", 200.0);
      boolean result = bank.withdraw(accountNumber, "1234", 100.0);
      assertTrue(result, "Withdraw should be successful");
    }

    @Test
    @DisplayName("Test to check that user can not withdraw negative money")
    public void testWithdrawNegativeMoney() {
      BankeBank bank = new BankeBank();
      String accountNumber = bank.createAccount("John", "Doe", "1234");
        bank.deposit(accountNumber, "1234", 200.0);
      boolean result = bank.withdraw(accountNumber, "1234", -100.0);
      assertFalse(result, "Withdraw should not be successful for negative money");
    }

    @Test
    @DisplayName("Test to check that user can not withdraw zero money")
    public void testWithdrawZeroMoney() {
      BankeBank bank = new BankeBank();
      String accountNumber = bank.createAccount("John", "Doe", "1234");
      bank.deposit(accountNumber, "1234", 200.0);
      boolean result = bank.withdraw(accountNumber, "1234", 0.0);
      assertFalse(result, "Withdraw should not be successful for zero money");
    }

    @Test
    @DisplayName("Test to check that user can not withdraw money from non existing account")
    public void testWithdrawFromNonExistingAccount() {
      BankeBank bank = new BankeBank();
      boolean result = bank.withdraw("9999", "1234", 100.0);
      assertFalse(result, "Withdraw should not be successful for non existing account");
    }

    @Test
    @DisplayName("Test to check that user can delete account")
    public void testDeleteAccount() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.closeAccount(accountNumber,"1234");
        assertTrue(result, "Account should be deleted");
    }

    @Test
    @DisplayName("Test to check that user can check account balance")
    public  void testCheckBalance() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        bank.deposit(accountNumber, "1234", 100.0);
        double balance = bank.getBalance(accountNumber, "1234");
        assertEquals(100.0, balance, "Balance should be 100.0 after deposit");
    }

    @Test
    @DisplayName("Test to check balance with wrong PIN")
    public void testCheckBalanceWithWrongPin() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        bank.deposit(accountNumber, "1234", 100.0);
        assertThrows(IllegalArgumentException.class, () -> bank.getBalance(accountNumber, "123"));
    }

    @Test
    @DisplayName("Test to check balance for non-existing account")
    public void testCheckBalanceWithWrongAccountNumber() {
        BankeBank bank = new BankeBank();
        assertThrows(IllegalArgumentException.class, () -> bank.getBalance("1234", "1234"),
            "Should throw exception for non-existing account"
        );
    }

    @Test
    @DisplayName("Test to check balance with invalid account number format")
    public void testCheckBalanceInvalidFormat() {
        BankeBank bank = new BankeBank();
        assertThrows(IllegalArgumentException.class, () -> bank.getBalance("abc", "1234"),
                "Should throw exception for invalid account number format");
    }

}
