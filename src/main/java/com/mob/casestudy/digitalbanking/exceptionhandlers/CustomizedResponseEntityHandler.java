package com.mob.casestudy.digitalbanking.exceptionhandlers;

import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.mob.casestudy.digitalbanking.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;

@RestControllerAdvice
public class CustomizedResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundExceptions.class)
    public final ResponseEntity<Object> handleAllNotFoundCustomException(NotFoundExceptions notFoundExceptions) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(notFoundExceptions.getCode(), notFoundExceptions.getDescription());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestExceptions.class)
    public final ResponseEntity<Object> handleAllBadRequestException(BadRequestExceptions badRequestExceptions) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(badRequestExceptions.getCode(), badRequestExceptions.getDescription());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse;
        if (ex.getMessage().contains(CreateCustomerRequest.class.getName())) {
            exceptionResponse = new ExceptionResponse(CUS_MAND_VALID_CODE, CUS_MAND_VALID_DESCRIPTION);
        }
        else {
            exceptionResponse = new ExceptionResponse("Unexpected Exception", ex.getMessage());
        }
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(PREF_LANG_CODE,PREF_LANG_DESCRIPTION);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
