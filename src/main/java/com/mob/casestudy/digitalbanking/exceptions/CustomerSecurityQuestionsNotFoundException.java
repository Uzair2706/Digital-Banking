package com.mob.casestudy.digitalbanking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerSecurityQuestionsNotFoundException extends RuntimeException {

    public CustomerSecurityQuestionsNotFoundException(String message) {
        super(message);
    }
}
