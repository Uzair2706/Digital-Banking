package com.mob.casestudy.digitalbanking.exceptions;

import lombok.Getter;

@Getter
public class BadRequestExceptions extends RuntimeException {

    private final String code;
    private final String description;

    public BadRequestExceptions(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
