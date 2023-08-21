package com.bank.system.validator;


import com.bank.system.enums.Direction;
import com.bank.system.exception.custom.AccountNotActiveStatusException;
import com.bank.system.exception.custom.CurrencyTransferShouldSameException;
import com.bank.system.exception.custom.SameAccountException;
import com.bank.system.exception.general.ProblemRunTimeException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;

import static com.bank.system.mockData.BankAccountMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountValidatorTest {

    BankAccountValidator bankAccountValidator = new BankAccountValidator();


    @Test
    public void testValidateAccounts_whenAccountNotActiveStatus() {
        ProblemRunTimeException exception = assertThrows(AccountNotActiveStatusException.class, () -> bankAccountValidator.validateAccounts(ACCOUNT_5000EGP(), ACCOUNT_5000EGP_SUSPENDED(), Direction.OUTWARD, new BigDecimal(1000)));
        assertEquals("Your account should be active status", exception.getErrorApiResponse().getMessage());
        assertEquals(1002, exception.getErrorApiResponse().getCode());
    }

    @Test
    public void testValidateAccounts_whenForeignAccountNotActiveStatus() {
        ProblemRunTimeException exception = assertThrows(AccountNotActiveStatusException.class, () -> bankAccountValidator.validateAccounts(ACCOUNT_5000EGP_SUSPENDED(), ACCOUNT_5000EGP(), Direction.OUTWARD, new BigDecimal(1000)));
        assertEquals("Your account should be active status", exception.getErrorApiResponse().getMessage());
        assertEquals(1002, exception.getErrorApiResponse().getCode());
    }

    @Test
    public void testValidateAccounts_whenTwoAccountsAreTheSame() {
        ProblemRunTimeException exception = assertThrows(SameAccountException.class, () -> bankAccountValidator.validateAccounts(ACCOUNT_5000EGP(), ACCOUNT_5000EGP(), Direction.OUTWARD, new BigDecimal(1000)));
        assertEquals("You can't send or receive money from your account", exception.getErrorApiResponse().getMessage());
        assertEquals(1003, exception.getErrorApiResponse().getCode());
    }

    @Test
    public void testValidateAccounts_whenCurrencyTransferShouldSame() {
        ProblemRunTimeException exception = assertThrows(CurrencyTransferShouldSameException.class, () -> bankAccountValidator.validateAccounts(ACCOUNT_5000USD(), ACCOUNT_5000EGP(), Direction.OUTWARD, new BigDecimal(1000)));
        assertEquals("The currency should be the same", exception.getErrorApiResponse().getMessage());
        assertEquals(1004, exception.getErrorApiResponse().getCode());
    }

    @Test
    public void testValidateAccounts_With_Outward_Direction_ValidationOK_WithoutException() {
        assertDoesNotThrow(() -> bankAccountValidator.validateAccounts(ACCOUNT_200EGP(), ACCOUNT_5000EGP(), Direction.OUTWARD, new BigDecimal(1000)));
    }

    @Test
    public void testValidateAccounts_With_Inward_Direction_ValidationOK_WithoutException() {
        assertDoesNotThrow(() -> bankAccountValidator.validateAccounts(ACCOUNT_5000EGP(), ACCOUNT_200EGP(), Direction.INWARD, new BigDecimal(1000)));
    }
}
