package com.bank.system.exception.custom;


import com.bank.system.exception.general.ProblemRunTimeException;

import static com.bank.system.exception.ErrorCodes.CURRENCY_SHOULD_SAME;

public class CurrencyTransferShouldSameException extends ProblemRunTimeException {

    public CurrencyTransferShouldSameException() {
        super(CURRENCY_SHOULD_SAME);
    }
}
