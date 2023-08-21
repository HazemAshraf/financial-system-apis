package com.bank.system.service;

import com.bank.system.dto.request.CreateBankAccount;


public interface BankAccountService {

    /**
     * bank-account : create bank account
     * create a new bank account associated to a given customer id
     *
     * @param createBankAccount (required) contains accountStatus, accountNumber, accountType, currency, customerId, accountNumber and balance
     *                    all fields are mandatory and validated
     * @return account-id of created account
     */
    Long createBankAccount(CreateBankAccount createBankAccount);

    /**
     * getBankAccount : get account by id
     *
     * @param accountId
     *
     * @return the saved BankAccount of account id
     *  or throw NotFoundException()
     */
    com.bank.system.dto.response.CreateBankAccount getBankAccount(Long accountId);

}
