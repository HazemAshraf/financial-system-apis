package com.bank.system.service.impl;

import com.bank.system.dto.response.CreateBankAccount;
import com.bank.system.exception.general.NotFoundException;
import com.bank.system.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.bank.system.mockData.AccountMock.bankAccountRequest;
import static com.bank.system.mockData.AccountMock.createdAccount;
import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BankAccountServiceImplTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    BankAccountServiceImpl bankAccountServiceImpl;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBankAccount_whenAccountSavedSuccessfulOnDatabase() {
        when(accountRepository.save(any())).thenReturn(createdAccount());
        Long accountId = bankAccountServiceImpl.createBankAccount(bankAccountRequest());
        assertEquals(111123124L, accountId.longValue());
    }

    @Test
    public void testGetBankAccount_whenNotFoundAccount() {
        when(accountRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> bankAccountServiceImpl.getBankAccount(anyLong()));
    }

    @Test
    public void testGetBankAccount_whenAccountSavedSuccessfulOnDatabase() {
        when(accountRepository.findById(any())).thenReturn(Optional.of(createdAccount()));
        CreateBankAccount bankAccount = bankAccountServiceImpl.getBankAccount(anyLong());
        assertEquals(createdAccount().getBalance(), bankAccount.getBalance());
        assertEquals(createdAccount().getAccountNumber(), bankAccount.getAccountNumber());
        assertEquals(createdAccount().getAccountType(), bankAccount.getAccountType());
        assertEquals(createdAccount().getAccountStatus(), bankAccount.getAccountStatus());
        assertEquals(createdAccount().getCurrency(), bankAccount.getCurrency());
        assertEquals(createdAccount().getId(), bankAccount.getId());
    }

}
