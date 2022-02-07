package com.mob.casestudy.digitalbanking.exceptions;

import lombok.Getter;

@Getter
public class CustomerNotFoundException extends RuntimeException {

    private final String code;
    private final String description;

    public CustomerNotFoundException(String message,String description) {
        super(message);
        this.code = message;
        this.description = description;
    }
}
