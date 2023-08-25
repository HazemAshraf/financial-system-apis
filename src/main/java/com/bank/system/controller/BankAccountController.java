package com.bank.system.controller;

import com.bank.system.dto.request.CreateBankAccount;
import com.bank.system.service.BankAccountService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/bank-account")
public class BankAccountController {


    @Autowired
    BankAccountService bankAccountServiceImpl;

    /**
     * POST /bank-account : create bank account
     * create a new bank account associated to a given customer id
     *
     * @param createBankAccount (required) contains accountStatus, accountNumber, accountType, currency, customerId and balance
     *                          all fields are mandatory and validated
     * @return Successful operation with empty body (status code 201) if it saved successful
     * headers uri reference with created account
     * Bad Request (status code 400) if any problem in request body
     */
    @PostMapping
    public ResponseEntity<?> createBankAccount(@Valid @RequestBody CreateBankAccount createBankAccount) {
        Long accountId = bankAccountServiceImpl.createBankAccount(createBankAccount);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("accountId", accountId.toString());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{accountId}")
                .buildAndExpand(accountId)
                .toUri();

        return ResponseEntity.created(location).headers(responseHeaders).build();
    }


    /**
     * GET /bank-account/{accountId} : get bank account by id
     * get bank account
     *
     * @param accountId ID of account to get (required)
     * @return BankAccountResponse the saved account details successful operation (status code 200)
     * or Not Found (status code 404)
     */
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<com.bank.system.dto.response.CreateBankAccount> getBankAccount(@PathVariable("accountId") Long accountId) {
        return new ResponseEntity<>(bankAccountServiceImpl.getBankAccount(accountId), HttpStatus.OK);
    }


}
