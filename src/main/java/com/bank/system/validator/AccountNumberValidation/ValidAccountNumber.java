package com.bank.system.validator.AccountNumberValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AccountNumberConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidAccountNumber {
   String message() default "Invalid Account Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
