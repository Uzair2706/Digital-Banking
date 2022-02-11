package com.mob.casestudy.digitalbanking.exceptionhandlers;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponse {

    private String errorCode;
    private String errorDescription;

    public ExceptionResponse(String errorCode, String description) {
        this.errorCode = errorCode;
        this.errorDescription = description;
    }
}
