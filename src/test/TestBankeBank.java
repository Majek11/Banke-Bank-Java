package test;

import app.BankeAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBankeBank {

    @Test
    @DisplayName("Test to check successful account creation")
    public void testCreateAccount() {
        BankeAccount bank = new BankeAccount();
        String accountNumber = bank.createAccount("John", "Doe", "1234");
        assertNotNull(accountNumber, "Account number should not be null");
    }

}
