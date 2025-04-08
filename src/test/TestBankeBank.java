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
        assertNotNull(accountNumber, "Account number should not be null");
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
    @DisplayName("Test to check if pin has leading/trailing spaces")
    public void testPinWithSpaces() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", " 1234 ");
        assertNull(accountNumber, "Account should return null if pin has leading/trailing spaces");
    }

    @Test
    @DisplayName("Test to check if user can deposit money")
    public void testDepositMoney() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit(accountNumber, 100.0);
        assertTrue(result,"Deposit should be be successful");
    }

    @Test
    @DisplayName("Test to check if user can not deposit negative money")
    public void testDepositNegativeMoney() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit(accountNumber, -100.0);
        assertFalse(result,"Deposit should not be be successful");
    }

    @Test
    @DisplayName("Test to check if user can not deposit zero money")
    public void testDepositZeroMoney() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit(accountNumber, 0.0);
    }

    @Test
    @DisplayName("Test to check if user can not deposit money to non existing account")
    public void testDepositToNonExistingAccount() {
        BankeBank bank = new BankeBank();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        boolean result = bank.deposit("invalidAccount", 100.0);
        assertFalse(result,"Deposit should not be be successful");
    }
}
