package com.bank.system.service.impl;

import com.bank.system.dto.response.GetTransactions;
import com.bank.system.exception.general.NotFoundException;
import com.bank.system.repository.AccountRepository;
import com.bank.system.repository.TransactionRepository;
import com.bank.system.validator.BankAccountValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;


import static com.bank.system.mockData.AccountMock.createdAccount;
import static com.bank.system.mockData.BankAccountMock.ACCOUNT_200EGP;
import static com.bank.system.mockData.BankAccountMock.ACCOUNT_5000EGP;
import static com.bank.system.mockData.TransactionsMock.transactionListPage;
import static com.bank.system.mockData.TransferModelMock.transferModel;
import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    BankAccountValidator bankAccountValidator;

    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
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
    public void testGetTransaction_whenTheWrongAccountId() {
        when(accountRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> transactionServiceImpl.getTransactions(anyLong(), 1, 1));
    }

    @Test
    public void testRefund_whenNotFoundFromAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> transactionServiceImpl.transferTransaction(transferModel()));
    }

    @Test
    public void testRefund_whenNotFoundToAccount() {
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> transactionServiceImpl.transferTransaction(transferModel()));
    }

    @Test
    public void testRefund_whenNotActiveAccountStatus() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(ACCOUNT_5000EGP()));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(ACCOUNT_200EGP()));
        doNothing().when(bankAccountValidator).validateAccounts(any(), any(), any(), any());
        assertDoesNotThrow(() -> transactionServiceImpl.transferTransaction(transferModel()));
    }

}
