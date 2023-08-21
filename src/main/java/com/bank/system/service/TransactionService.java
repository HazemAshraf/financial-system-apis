package com.bank.system.service;

import com.bank.system.dto.request.CreateTransferTransaction;
import com.bank.system.dto.response.GetTransactions;
import java.util.List;

public interface TransactionService {


    /**
     * financial transfer transaction: transfer money from bank account to another account
     *    needs to make sure same currency, the account/foreignAccount have available money to transfer with active status
     *    save the transaction history with the amount before and after of two accounts
     *    settle the balance of two accounts
     * @param createTransferTransaction (required)
     */
    void transferTransaction(CreateTransferTransaction createTransferTransaction);

    /**
     * getTransactions: Retrieve transactions history for a given account.
     *      the transaction contains the sender and receiver and amount before and after every transaction
     *      with transaction Type and currency ordered by createdAt date
     *
     * @param accountId (required) given account id
     *
     * @return List of Transaction ordered by created at DESC
     */
    List<GetTransactions> getTransactions(Long accountId, int page, int pageSize);
}
