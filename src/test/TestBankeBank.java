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

    @Test
    @DisplayName("Test to check successful transfer between accounts")
    public void testTransferSuccess() {
        BankeBank bank = new BankeBank();
        String account1 = bank.createAccount("John", "Doe", "1234");
        String account2 = bank.createAccount("Jane", "Smith", "5678");
        bank.deposit(account1, "1234", 200.0);
        boolean result = bank.transfer(account1, "1234", account2, 100.0);
        assertTrue(result, "Transfer should be successful");
        assertEquals(100.0, bank.getBalance(account1, "1234"), "Source account should have 100.0 left");
        assertEquals(100.0, bank.getBalance(account2, "5678"), "Destination account should have 100.0");
    }

    @Test
    @DisplayName("Test to check transfer with insufficient funds")
    public void testTransferInsufficientFunds() {
        BankeBank bank = new BankeBank();
        String account1 = bank.createAccount("John", "Doe", "1234");
        String account2 = bank.createAccount("Jane", "Smith", "5678");
        bank.deposit(account1, "1234", 200.0);
        boolean result = bank.transfer(account1, "1234", account2, 300.0);
        assertFalse(result, "Transfer should not be successful");
        assertEquals(200.0, bank.getBalance(account1, "1234"), "Source account should have 200.0 left");
        assertEquals(0.0, bank.getBalance(account2, "5678"), "Destination account should have 0.0");
    }

    @Test
    @DisplayName("Test to check transfer to non-existing account")
    public void testTransferToNonExistingAccount() {
        BankeBank bank = new BankeBank();
        String account1 = bank.createAccount("John", "Doe", "1234");
        bank.deposit(account1, "1234", 200.0);
        boolean result = bank.transfer(account1, "1234", "9999", 100.0);
        assertFalse(result, "Transfer should not be successful for non-existing account");
        assertEquals(200.0, bank.getBalance(account1, "1234"), "Source account should have 200.0 left");
    }

    @Test
    @DisplayName("Test to check transfer with wrong PIN")
    public void testTransferWithWrongPin() {
        BankeBank bank = new BankeBank();
        String account1 = bank.createAccount("John", "Doe", "1234");
        String account2 = bank.createAccount("Jane", "Smith", "5678");
        bank.deposit(account1, "1234", 200.0);
        boolean result = bank.transfer(account1, "1253", account2, 100.0);
        assertFalse(result, "Transfer should not be successful for wrong PIN");
        assertEquals(200.0, bank.getBalance(account1, "1234"), "Source account should have 200.0 left");
        assertEquals(0.0, bank.getBalance(account2, "5678"), "Destination account should have 0.0");
    }

    @Test
    @DisplayName("Test to check successful PIN change")
    public void testChangePinSuccess() {
        BankeBank bank = new BankeBank();
        String account = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.changePin(account, "1234", "5678");
        assertTrue(result, "PIN change should be successful");
        assertEquals(0.0, bank.getBalance(account, "5678"), "New PIN should work"); // Verify new PIN
    }

    @Test
    @DisplayName("Test to check PIN change with wrong old PIN")
    public void testChangePinWrongOldPin() {
        BankeBank bank = new BankeBank();
        String account = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.changePin(account, "9999", "5678");
        assertFalse(result, "PIN change should fail with wrong old PIN");
    }

    @Test
    @DisplayName("Test to check PIN change with invalid new PIN")
    public void testChangePinInvalidNewPin() {
        BankeBank bank = new BankeBank();
        String account = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.changePin(account, "1234", "abc"); // Not numeric
        assertFalse(result, "PIN change should fail with invalid new PIN");
    }






}


