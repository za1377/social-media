package com.webservice.restapiwebservice.ExceptionError;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNOtFoundException extends RuntimeException {
    public PostNOtFoundException(String message) {
        super(message);
    }
}
