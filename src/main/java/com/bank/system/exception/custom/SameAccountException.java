package com.bank.system.exception.custom;

import com.bank.system.exception.general.ProblemRunTimeException;

import static com.bank.system.exception.ErrorCodes.SAME_ACCOUNT;

public class SameAccountException extends ProblemRunTimeException {

    public SameAccountException() {
        super(SAME_ACCOUNT);
    }
}
