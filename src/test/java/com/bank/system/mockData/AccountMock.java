package com.bank.system.mockData;

import com.bank.system.entity.Account;
import com.bank.system.entity.Customer;
import com.bank.system.entity.Transaction;
import com.bank.system.dto.request.CreateBankAccount;
import com.bank.system.enums.AccountStatus;
import com.bank.system.enums.AccountType;
import com.bank.system.enums.Currency;
import com.bank.system.enums.Direction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountMock {

   public static final Account createdAccount(){
       Account account = new Account();
       account.setAccountType(AccountType.SAVING);
       account.setAccountStatus(AccountStatus.ACTIVE);
       account.setCreatedAt(new Date());
       account.setAccountNumber("19209393102310");
       account.setBalance(new BigDecimal(1500));
       account.setCurrency(Currency.USD);
       account.setId(111123124L);
       Customer customer = new Customer();
       customer.setId(123123L);
       customer.setName("Youssef Ahmed");
       account.setCustomer(customer);
       List<Transaction> transactionList = new ArrayList<>();
       Transaction transaction = new Transaction();
       transaction.setAccount(account);
       transaction.setForeignAccount(account);
       transaction.setAmount(new BigDecimal(500));
       transaction.setId(234234L);
       transaction.setAccountBalanceBeforeTransaction(new BigDecimal(1000));
       transaction.setAccountBalanceAfterTransaction(new BigDecimal(1500));
       transaction.setDirection(Direction.INWARD);
       transactionList.add(transaction);
       account.setTransactions(transactionList);
       return account;
   }

    public static final CreateBankAccount bankAccountRequest(){
        CreateBankAccount account = new CreateBankAccount();
        account.setAccountType(AccountType.SAVING);
        account.setCurrency(Currency.EGP);
        account.setBalance(new BigDecimal(3000));
        account.setAccountNumber("78827891278979");
        account.setAccountStatus(AccountStatus.ACTIVE);
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Mohamed Ahmed");
        account.setCustomerId(1L);
        return account;
    }

    public static final com.bank.system.dto.response.CreateBankAccount bankAccountResponse(){
        com.bank.system.dto.response.CreateBankAccount account = new com.bank.system.dto.response.CreateBankAccount();
        account.setAccountType(AccountType.SAVING);
        account.setCurrency(Currency.EGP);
        account.setBalance(new BigDecimal(3000));
        account.setAccountNumber("89789234239233");
        account.setId(1L);
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Mohamed Ahmed");
        account.setCustomerId(1L);
        account.setAccountStatus(AccountStatus.ACTIVE);
        return account;
    }
    public static final CreateBankAccount bankAccountRequestWithNullAccountType(){
        CreateBankAccount account = new CreateBankAccount();
        account.setCurrency(Currency.EGP);
        account.setBalance(new BigDecimal(3000));
        account.setAccountNumber("78237847823478");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Mohamed Nasser");
        account.setCustomerId(1L);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setAccountType(null);
        return account;
    }

    public static final CreateBankAccount bankAccountRequestWithIncorrectAccountNumber(){
        CreateBankAccount account = new CreateBankAccount();
        account.setCurrency(Currency.EGP);
        account.setBalance(new BigDecimal(3000));
        account.setAccountNumber("7823784782");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Mohamed Nasser");
        account.setCustomerId(1L);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setAccountType(AccountType.SAVING);
        return account;
    }

    public static final CreateBankAccount bankAccountRequestWithAccountNumberlengthLessThan_14(){
        CreateBankAccount account = new CreateBankAccount();
        account.setCurrency(Currency.EGP);
        account.setBalance(new BigDecimal(3000));
        account.setAccountNumber("78237847823");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ahmed Nasser");
        account.setCustomerId(1L);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setAccountType(AccountType.SAVING);
        return account;
    }


}
