package com.webservice.restapiwebservice.ExceptionError;

import java.time.LocalDateTime;

public class ErrorDetail {

    private String message;
    private String details;
    private LocalDateTime timeStamp;

    public ErrorDetail(String message, String details, LocalDateTime timeStamp) {
        this.message = message;
        this.details = details;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
