package com.webservice.restapiwebservice.ExceptionError;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetail> handleAllException (Exception ex, WebRequest request) throws Exception{
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNOtFoundException.class)
    public final ResponseEntity<ErrorDetail> userNotFoundException (Exception ex, WebRequest request) throws Exception{
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(ex.getFieldError().getDefaultMessage(),
                request.getDescription(false), LocalDateTime.now());
        System.out.println("here");
        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }
}
