package com.bank.system.exception.custom;


import com.bank.system.exception.general.ProblemRunTimeException;

import static com.bank.system.exception.ErrorCodes.ACCOUNT_NOT_ACTIVE_STATUS;

public class AccountNotActiveStatusException extends ProblemRunTimeException {

    public AccountNotActiveStatusException() {
        super(ACCOUNT_NOT_ACTIVE_STATUS);
    }
}
