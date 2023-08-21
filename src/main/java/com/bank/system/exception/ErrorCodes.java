package com.bank.system.exception;


import com.bank.system.dto.response.ErrorApiResponse;

public class ErrorCodes {

    private ErrorCodes() {
        throw new IllegalStateException("ErrorCodes class");
    }

    public static final ErrorApiResponse NOT_HAS_ENOUGH_MONEY = new ErrorApiResponse(1001, "No has enough money to transfer");
    public static final ErrorApiResponse ACCOUNT_NOT_ACTIVE_STATUS = new ErrorApiResponse(1002, "Your account should be active status");
    public static final ErrorApiResponse SAME_ACCOUNT = new ErrorApiResponse(1003, "You can't send or receive money from your account");
    public static final ErrorApiResponse CURRENCY_SHOULD_SAME = new ErrorApiResponse(1004, "The currency should be the same");

}
