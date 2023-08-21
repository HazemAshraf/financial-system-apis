package com.bank.system.service.impl;

import com.bank.system.dto.request.CreateTransferTransaction;
import com.bank.system.dto.response.GetTransactions;
import com.bank.system.entity.Account;
import com.bank.system.entity.Transaction;
import com.bank.system.enums.Direction;
import com.bank.system.enums.TransactionType;
import com.bank.system.exception.general.NotFoundException;
import com.bank.system.repository.AccountRepository;
import com.bank.system.repository.TransactionRepository;
import com.bank.system.service.TransactionService;
import com.bank.system.validator.BankAccountValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final BankAccountValidator bankAccountValidator;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, BankAccountValidator bankAccountValidator) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.bankAccountValidator = bankAccountValidator;
    }


    @Override
    @Transactional
    public void transferTransaction(CreateTransferTransaction createTransferTransaction) {
        Account account = getAccountById(createTransferTransaction.getAccount());
        Account foreignAccount = getAccountById(createTransferTransaction.getForeignAccount());
        bankAccountValidator.validateAccounts(foreignAccount, account, createTransferTransaction.getDirection(), createTransferTransaction.getAmount());
        BigDecimal accountBalanceBeforeTransfer = account.getBalance();
        BigDecimal foreignAccountBalanceBeforeTransfer = foreignAccount.getBalance();
        BigDecimal accountBalanceAfterTransfer = settleBalanceForAccount(account, createTransferTransaction);
        BigDecimal foreignAccountBalanceAfterTransfer = settleBalanceForForeignAccount(foreignAccount, createTransferTransaction);

        // run transaction from account perspective
        runTransaction(createTransferTransaction.getTransactionType(), createTransferTransaction.getDirection(), createTransferTransaction.getAmount(), account, foreignAccount, accountBalanceAfterTransfer, accountBalanceBeforeTransfer);
        // run transaction from foreignAccount perspective
        runTransaction(createTransferTransaction.getTransactionType(), createTransferTransaction.getDirection() == Direction.OUTWARD ? Direction.INWARD : Direction.OUTWARD, createTransferTransaction.getAmount(), foreignAccount, account, foreignAccountBalanceAfterTransfer, foreignAccountBalanceBeforeTransfer);
    }

    private Account getAccountById(long accountId) {
        return accountRepository.findById(accountId).orElseThrow(NotFoundException::new);
    }

    private BigDecimal settleBalanceForForeignAccount(Account foreignAccount, CreateTransferTransaction createTransferTransaction) {
        if (createTransferTransaction.getDirection().equals(Direction.OUTWARD)) {
            return foreignAccount.getBalance().add(createTransferTransaction.getAmount());
        } else {
            return foreignAccount.getBalance().subtract(createTransferTransaction.getAmount());
        }
    }

    private BigDecimal settleBalanceForAccount(Account account, CreateTransferTransaction createTransferTransaction) {
        if (createTransferTransaction.getDirection().equals(Direction.OUTWARD)) {
            return account.getBalance().subtract(createTransferTransaction.getAmount());
        } else {
            return account.getBalance().add(createTransferTransaction.getAmount());
        }
    }

    private void runTransaction(TransactionType transactionType, Direction direction, BigDecimal amount, Account account, Account foreignAccount, BigDecimal accountBalanceAfterTransfer, BigDecimal accountBalanceBeforeTransfer) {
        account.setBalance(accountBalanceAfterTransfer);
        Transaction transaction = createTransaction(transactionType, direction, account, foreignAccount, amount, accountBalanceBeforeTransfer, accountBalanceAfterTransfer);
        account.getTransactions().add(transaction);
        accountRepository.save(account);
    }


    @Override
    public List<GetTransactions> getTransactions(Long accountId, int page, int pageSize) {
        Account account = accountRepository.findById(accountId).orElseThrow(NotFoundException::new);
        Page<Transaction> transactions = transactionRepository.findByAccountOrderByCreatedAt(account, getPageable(page, pageSize));
        if (transactions.isEmpty())
            throw new NotFoundException();
        return transactions.stream().map(GetTransactions::new).collect(Collectors.toList());
    }

    private static Pageable getPageable(int page, int pageSize) {
        return PageRequest.of((page - 1), pageSize);
    }

    private Transaction createTransaction(TransactionType transactionType, Direction direction, Account account, Account foreignAccount, BigDecimal amount, BigDecimal accountBalanceBeforeTransfer, BigDecimal accountBalanceAfterTransfer) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setDirection(direction);
        transaction.setAccount(account);
        transaction.setForeignAccount(foreignAccount);
        transaction.setAmount(amount);
        transaction.setAccountBalanceBeforeTransaction(accountBalanceBeforeTransfer);
        transaction.setAccountBalanceAfterTransaction(accountBalanceAfterTransfer);
        return transaction;
    }

}
