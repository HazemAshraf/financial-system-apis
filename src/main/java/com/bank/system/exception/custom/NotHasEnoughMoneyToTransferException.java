package com.bank.system.exception.custom;

import com.bank.system.exception.general.ProblemRunTimeException;

import static com.bank.system.exception.ErrorCodes.NOT_HAS_ENOUGH_MONEY;

public class NotHasEnoughMoneyToTransferException extends ProblemRunTimeException {

    public NotHasEnoughMoneyToTransferException() {
        super(NOT_HAS_ENOUGH_MONEY);
    }
}
