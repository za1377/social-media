package com.webservice.restapiwebservice.ExceptionError;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNOtFoundException extends RuntimeException {
    public UserNOtFoundException(String message) {
        super(message);
    }
}
