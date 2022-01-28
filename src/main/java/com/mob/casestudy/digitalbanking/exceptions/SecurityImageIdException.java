package com.mob.casestudy.digitalbanking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SecurityImageIdException extends RuntimeException {

    public SecurityImageIdException(String message) {
        super(message);
    }

}
