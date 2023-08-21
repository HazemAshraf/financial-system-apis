package com.bank.system.mapper;

import com.bank.system.dto.request.CreateBankAccount;
import com.bank.system.entity.Account;
import com.bank.system.entity.Customer;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T17:46:42+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public Account toAccountEntity(CreateBankAccount createBankAccount) {
        if ( createBankAccount == null ) {
            return null;
        }

        Account account = new Account();

        account.setCustomer( createBankAccountToCustomer( createBankAccount ) );
        account.setBalance( createBankAccount.getBalance() );
        account.setAccountNumber( createBankAccount.getAccountNumber() );
        account.setAccountStatus( createBankAccount.getAccountStatus() );
        account.setAccountType( createBankAccount.getAccountType() );
        account.setCurrency( createBankAccount.getCurrency() );

        return account;
    }

    @Override
    public com.bank.system.dto.response.CreateBankAccount toBankAccount(Account account) {
        if ( account == null ) {
            return null;
        }

        com.bank.system.dto.response.CreateBankAccount.CreateBankAccountBuilder createBankAccount = com.bank.system.dto.response.CreateBankAccount.builder();

        createBankAccount.customerId( accountCustomerId( account ) );
        createBankAccount.id( account.getId() );
        createBankAccount.accountNumber( account.getAccountNumber() );
        createBankAccount.balance( account.getBalance() );
        createBankAccount.accountStatus( account.getAccountStatus() );
        createBankAccount.accountType( account.getAccountType() );
        createBankAccount.currency( account.getCurrency() );
        createBankAccount.createdAt( account.getCreatedAt() );

        return createBankAccount.build();
    }

    protected Customer createBankAccountToCustomer(CreateBankAccount createBankAccount) {
        if ( createBankAccount == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( createBankAccount.getCustomerId() );

        return customer;
    }

    private Long accountCustomerId(Account account) {
        if ( account == null ) {
            return null;
        }
        Customer customer = account.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
