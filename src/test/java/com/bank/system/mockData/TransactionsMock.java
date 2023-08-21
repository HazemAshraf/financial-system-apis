package com.bank.system.mockData;

import com.bank.system.dto.response.GetTransactions;
import com.bank.system.entity.Transaction;
import com.bank.system.enums.Direction;
import com.bank.system.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bank.system.mockData.AccountMock.createdAccount;
import static com.bank.system.mockData.BankAccountMock.ACCOUNT_200EGP;

public class TransactionsMock {

    public static final List<GetTransactions> transactionListResponse() {
        List<GetTransactions> transactionsResponseList = new ArrayList<>();
        GetTransactions transactionsResponse = new GetTransactions();
        transactionsResponse.setTransactionType(TransactionType.DEPOSIT);
        transactionsResponse.setAccountBalanceAfterTransaction(new BigDecimal(1000));
        transactionsResponse.setAccountBalanceBeforeTransaction(new BigDecimal(2000));
        transactionsResponse.setAccountId(122L);
        transactionsResponse.setForeignAccountId(123123L);
        transactionsResponse.setAmount(new BigDecimal(1000));
        transactionsResponse.setDirection(Direction.OUTWARD);
        transactionsResponse.setId(222L);
        transactionsResponse.setCreatedAt(new Date());
        transactionsResponseList.add(transactionsResponse);
        return transactionsResponseList;
    }

    public static final Page<Transaction> transactionListPage() {
        List<Transaction> transactionsResponseList = new ArrayList<>();
        Transaction transactionsResponse = new Transaction();
        transactionsResponse.setTransactionType(TransactionType.DEPOSIT);
        transactionsResponse.setAccountBalanceAfterTransaction(new BigDecimal(1500));
        transactionsResponse.setAccountBalanceBeforeTransaction(new BigDecimal(1000));
        transactionsResponse.setAccount(createdAccount());
        transactionsResponse.setForeignAccount(createdAccount());
        transactionsResponse.setAmount(new BigDecimal(500));
        transactionsResponse.setDirection(Direction.INWARD);
        transactionsResponse.setId(234234L);
        transactionsResponse.setCreatedAt(new Date());
        transactionsResponseList.add(transactionsResponse);

        Page<Transaction> transactionPage = new PageImpl<>(transactionsResponseList);

        return transactionPage;
    }

}
