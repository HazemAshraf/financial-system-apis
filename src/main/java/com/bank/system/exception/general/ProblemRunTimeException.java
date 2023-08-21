package com.bank.system.exception.general;

import com.bank.system.dto.response.ErrorApiResponse;
import lombok.Getter;

@Getter
public class ProblemRunTimeException extends RuntimeException {

    public final ErrorApiResponse errorApiResponse;

    public ProblemRunTimeException(ErrorApiResponse errorApiResponse) {
        super(errorApiResponse.getMessage());
        this.errorApiResponse = errorApiResponse;
    }


}
