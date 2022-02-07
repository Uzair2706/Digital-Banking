package com.mob.casestudy.digitalbanking.exceptionhandlers;

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
import static com.mob.casestudy.digitalbanking.constants.Constants.*;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomException(CustomerNotFoundException customerNotFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(customerNotFoundException.getCode(), customerNotFoundException.getDescription());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerSecurityQuestionsNotFoundException.class)
    public final ResponseEntity<Object> handleSecurityQuestions(CustomerSecurityQuestionsNotFoundException customerSecurityQuestionsNotFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(SECURITY_QUESTION_NOT_FOUND_CODE, customerSecurityQuestionsNotFoundException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TemplateIdException.class)
    public final ResponseEntity<Object> handleSecurityQuestions(TemplateIdException templateIdException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(TEMPLATE_ID_NOT_VALID, TEMPLATE_ID_NOT_VALID_DESCRIPTION);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonUniqueUserNameException.class)
    public final ResponseEntity<Object> handleSecurityQuestions(NonUniqueUserNameException nonUniqueUserNameException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(NON_UNIQUE_USERNAME_CODE, NON_UNIQUE_USERNAME_DESCRIPTION);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponse exceptionResponse;

        if (ex.getMessage().contains(CAPTION_NOT_NULL_DESCRIPTION)) {
            exceptionResponse = new ExceptionResponse(SECURITY_IMAGE_TEXT_NOT_VALID_CODE, CAPTION_NOT_NULL_DESCRIPTION);
        } else if (ex.getMessage().contains(CAPTION_NOT_EMPTY_DESCRIPTION)) {
            exceptionResponse = new ExceptionResponse(SECURITY_IMAGE_TEXT_NOT_VALID_CODE, CAPTION_NOT_EMPTY_DESCRIPTION);
        } else if (ex.getMessage().contains(CAPTION_SIZE_NOT_VALID_DESCRIPTION)) {
            exceptionResponse = new ExceptionResponse(SECURITY_IMAGE_TEXT_NOT_VALID_CODE, CAPTION_SIZE_NOT_VALID_DESCRIPTION);
        } else if (ex.getMessage().contains(FIELD_NOT_FOUND_DESCRIPTION)) {
            exceptionResponse = new ExceptionResponse(FIELD_NOT_FOUND_CODE, FIELD_NOT_FOUND_DESCRIPTION);
        } else {
            exceptionResponse = new ExceptionResponse("Unexpected Exception", ex.getMessage());
        }
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        String defaultMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
//        ExceptionResponse exceptionResponse = new ExceptionResponse(SECURITY_IMAGE_TEXT_NOT_VALID_CODE,defaultMessage);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(SecurityImageIdException.class)
    public final ResponseEntity<Object> handleException(SecurityImageIdException securityImageIdException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(SECURITY_IMAGE_NOT_FOUND_CODE, securityImageIdException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handle(Exception exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(PREF_LANG_CODE,PREF_LANG_DESCRIPTION);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
