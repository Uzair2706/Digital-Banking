package com.mob.casestudy.digitalbanking.exceptions;

public class ExceptionResponse {

    private String errorCode;
    private String errorDescription;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String errorCode, String description) {
        this.errorCode = errorCode;
        this.errorDescription = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
