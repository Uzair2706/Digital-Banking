package com.mob.casestudy.digitalbanking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MandatoryFieldNotValidatedException extends RuntimeException {

    public MandatoryFieldNotValidatedException() {
        super();
    }
}
