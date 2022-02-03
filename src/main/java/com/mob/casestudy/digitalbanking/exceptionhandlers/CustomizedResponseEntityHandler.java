package com.mob.casestudy.digitalbanking.exceptionhandlers;

import com.mob.casestudy.digitalbanking.constants.Constants;
import com.mob.casestudy.digitalbanking.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomException(CustomerNotFoundException customerNotFoundException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(customerNotFoundException.getCode(),customerNotFoundException.getDescription());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerSecurityQuestionsNotFoundException.class)
    public final ResponseEntity<Object> handleSecurityQuestions(CustomerSecurityQuestionsNotFoundException customerSecurityQuestionsNotFoundException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(Constants.SECURITY_QUESTION_NOT_FOUND_CODE,customerSecurityQuestionsNotFoundException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TemplateIdException.class)
    public final ResponseEntity<Object> handleSecurityQuestions(TemplateIdException templateIdException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(Constants.TEMPLATE_ID_NOT_VALID,Constants.TEMPLATE_ID_NOT_VALID_DESCRIPTION);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponse exceptionResponse;

        if(ex.getMessage().contains(Constants.CAPTION_NOT_NULL_DESCRIPTION)){
            exceptionResponse = new ExceptionResponse(Constants.SECURITY_IMAGE_TEXT_NOT_VALID_CODE,Constants.CAPTION_NOT_NULL_DESCRIPTION);
        }
        else if (ex.getMessage().contains(Constants.CAPTION_NOT_EMPTY_DESCRIPTION)){
            exceptionResponse = new ExceptionResponse(Constants.SECURITY_IMAGE_TEXT_NOT_VALID_CODE,Constants.CAPTION_NOT_EMPTY_DESCRIPTION);
        }
        else if (ex.getMessage().contains(Constants.CAPTION_SIZE_NOT_VALID_DESCRIPTION)){
            exceptionResponse = new ExceptionResponse(Constants.SECURITY_IMAGE_TEXT_NOT_VALID_CODE,Constants.CAPTION_SIZE_NOT_VALID_DESCRIPTION);
        }
        else if (ex.getMessage().contains(Constants.FIELD_NOT_FOUND_DESCRIPTION)){
            exceptionResponse = new ExceptionResponse(Constants.FIELD_NOT_FOUND_CODE,Constants.FIELD_NOT_FOUND_DESCRIPTION);
        }
        else{
            exceptionResponse = new ExceptionResponse("Unexpected Exception",ex.getMessage());
        }
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SecurityImageIdException.class)
    public final ResponseEntity<Object> handleException(SecurityImageIdException securityImageIdException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(Constants.SECURITY_IMAGE_NOT_FOUND_CODE,securityImageIdException.getMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

}
