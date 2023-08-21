package com.bank.system.validator.AccountNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountNumberConstraintValidator implements ConstraintValidator<ValidAccountNumber,String> {
    @Override
    public void initialize(ValidAccountNumber validAccountNumber) {
    }

    @Override
    public boolean isValid(String accountNumber, ConstraintValidatorContext constraintValidatorContext) {
        return accountNumber != null && accountNumber.length() >= 14;
    }
}
