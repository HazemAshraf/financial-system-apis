package com.bank.system.service.impl;

import com.bank.system.dto.response.CreateBankAccount;
import com.bank.system.dto.response.GetTransactions;
import com.bank.system.exception.general.NotFoundException;
import com.bank.system.repository.AccountRepository;
import com.bank.system.repository.TransactionRepository;
import com.bank.system.validator.BankAccountValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.bank.system.mockData.AccountMock.bankAccountRequest;
import static com.bank.system.mockData.AccountMock.createdAccount;
import static com.bank.system.mockData.BankAccountMock.*;
import static com.bank.system.mockData.TransactionsMock.transactionListPage;
import static com.bank.system.mockData.TransferModelMock.transferModel;
import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BankAccountServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    BankAccountValidator bankAccountValidator;

    @InjectMocks
    BankAccountServiceImpl bankAccountServiceImpl;

    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("when the created bank account saved successful on Database")
    public void testCreateBankAccount_whenAccountSavedSuccessfulOnDatabase() {
        when(accountRepository.save(any())).thenReturn(createdAccount());
        Long accountId = bankAccountServiceImpl.createBankAccount(bankAccountRequest());
        assertEquals(111123124L, accountId.longValue());
    }

    @Test
    @DisplayName("when trying get a wrong accountId not in Account table")
    public void testGetBankAccount_whenNotFoundAccount() {
        when(accountRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> bankAccountServiceImpl.getBankAccount(anyLong()));
    }

    @Test
    @DisplayName("when trying get bank account saved successful on Database")
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

    @Test
    @DisplayName("when trying get transactions for given account id")
    public void testGetTransaction_whenTheAccountHasTransactionSavedSuccessfulOnDatabase() {
        when(accountRepository.findById(any())).thenReturn(Optional.of(createdAccount()));
        when(transactionRepository.findByAccountOrderByCreatedAt(any(), any())).thenReturn(transactionListPage());
        List<GetTransactions> transactionsResponseList = transactionServiceImpl.getTransactions(anyLong(), 1, 1);
        assertEquals(createdAccount().getTransactions().get(0).getAccount().getId(), transactionsResponseList.get(0).getAccountId());
        assertEquals(createdAccount().getTransactions().get(0).getAccountBalanceAfterTransaction(), transactionsResponseList.get(0).getAccountBalanceAfterTransaction());
        assertEquals(createdAccount().getTransactions().get(0).getId(), transactionsResponseList.get(0).getId());
        assertEquals(createdAccount().getTransactions().get(0).getAmount(), transactionsResponseList.get(0).getAmount());
        assertEquals(createdAccount().getTransactions().get(0).getDirection().name(), transactionsResponseList.get(0).getDirection().name());
        assertEquals(createdAccount().getTransactions().get(0).getAccountBalanceBeforeTransaction(), transactionsResponseList.get(0).getAccountBalanceBeforeTransaction());
        assertEquals(createdAccount().getTransactions().get(0).getForeignAccount().getId(), transactionsResponseList.get(0).getForeignAccountId());
    }

    @Test
    @DisplayName("when trying get transaction for wrong account id")
    public void testGetTransaction_whenTheWrongAccountId() {
        when(accountRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> transactionServiceImpl.getTransactions(anyLong(), 1, 1));
    }

    @Test
    @DisplayName("when transfer money from wrong account")
    public void testRefund_whenNotFoundFromAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> transactionServiceImpl.transferTransaction(transferModel()));
    }

    @Test
    @DisplayName("when transfer money from wrong foreign account")
    public void testRefund_whenNotFoundToAccount() {
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> transactionServiceImpl.transferTransaction(transferModel()));
    }

    @Test
    @DisplayName("when transfer money from account without any exception - success case")
    public void testRefund_whenNotActiveAccountStatus() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(ACCOUNT_5000EGP()));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(ACCOUNT_200EGP()));
        doNothing().when(bankAccountValidator).validateAccounts(any(), any(), any(), any());
        assertDoesNotThrow(() -> transactionServiceImpl.transferTransaction(transferModel()));
    }

}
