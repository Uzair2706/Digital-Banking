package com.mob.casestudy.digitalbanking.exceptions;

import lombok.Getter;

@Getter
public class NotFoundExceptions extends RuntimeException {

    private final String code;
    private final String description;

    public NotFoundExceptions(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
