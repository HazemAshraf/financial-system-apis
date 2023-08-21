package com.bank.system.mockData;

import com.bank.system.entity.Account;
import com.bank.system.enums.AccountStatus;
import com.bank.system.enums.AccountType;
import com.bank.system.enums.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class BankAccountMock {

    public static final Account ACCOUNT_200EGP() {
        Account account = new Account();
        account.setId(111123124L);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal(200));
        account.setCurrency(Currency.EGP);
        account.setCreatedAt(new Date());
        account.setAccountType(AccountType.SAVING);
        account.setTransactions(new ArrayList<>());

        return account;
    }

    public static final Account ACCOUNT_5000EGP() {
        Account foreignAccount = new Account();
        foreignAccount.setId(1L);
        foreignAccount.setAccountStatus(AccountStatus.ACTIVE);
        foreignAccount.setBalance(new BigDecimal(5000));
        foreignAccount.setCurrency(Currency.EGP);
        foreignAccount.setCreatedAt(new Date());
        foreignAccount.setAccountType(AccountType.SAVING);
        foreignAccount.setTransactions(new ArrayList<>());
        return foreignAccount;
    }

    public static final Account ACCOUNT_5000USD() {
        Account foreignAccount = new Account();
        foreignAccount.setId(3L);
        foreignAccount.setAccountStatus(AccountStatus.ACTIVE);
        foreignAccount.setBalance(new BigDecimal(5000));
        foreignAccount.setCurrency(Currency.USD);
        foreignAccount.setCreatedAt(new Date());
        foreignAccount.setAccountType(AccountType.SAVING);
        foreignAccount.setTransactions(new ArrayList<>());

        return foreignAccount;
    }

    public static final Account ACCOUNT_5000EGP_SUSPENDED() {
        Account foreignAccount = new Account();
        foreignAccount.setId(2L);
        foreignAccount.setAccountStatus(AccountStatus.SUSPENDED);
        foreignAccount.setBalance(new BigDecimal(5000));
        foreignAccount.setCurrency(Currency.EGP);
        foreignAccount.setCreatedAt(new Date());
        foreignAccount.setAccountType(AccountType.SAVING);
        foreignAccount.setTransactions(new ArrayList<>());

        return foreignAccount;
    }
}
