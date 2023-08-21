package com.bank.system.controller;

import com.bank.system.dto.request.CreateTransferTransaction;
import com.bank.system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {


    @Autowired
    TransactionService transactionServiceImpl;


    /**
     * POST / : financial transfer transaction
     * transfer money from bank account to another account
     * needs to make sure same currency, the account/foreignAccount have available money to transfer with active status
     * settle the new balances and save the transaction history with the amount before and after in two accounts
     *
     * @param createTransferTransaction (required)
     * @return successful operation (status code 200)
     * or Bad Request (status code 400)
     */
    @PostMapping
    public ResponseEntity<?> createTransferTransaction(@Valid @RequestBody CreateTransferTransaction createTransferTransaction) {
        transactionServiceImpl.transferTransaction(createTransferTransaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET / : paginated transactions for a given account.
     * Retrieve transactions history for a given account.
     * the transaction contains the actual account, the foreign account, the transferred amount, the direction(INWARD/OUTWARD) and the balance before and after every transaction
     * with transaction Type and currency ordered by createdAt date
     *
     * @param accountId ID of account to get (required)
     * @param page      the intended page to get (required)
     * @param pageSize  the intended page to get (required)
     * @return List<GetTransaction> successful operation ordered by createdAt (status code 200)
     * or Not Found (status code 404)
     */

    @GetMapping
    public ResponseEntity<?> getTransactionsByAccountId(@Valid @RequestParam("accountId") Long accountId, @Valid @RequestParam(value = "page") int page, @Valid @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(transactionServiceImpl.getTransactions(accountId, page, pageSize), HttpStatus.OK);
    }

}
