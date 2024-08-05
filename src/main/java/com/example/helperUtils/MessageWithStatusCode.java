package com.example.helperUtils;

import org.springframework.http.HttpStatus;

import com.example.entity.Message;

public class MessageWithStatusCode {
    private Message message;
    private HttpStatus httpStatus;

    public MessageWithStatusCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public MessageWithStatusCode(Message message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    
}
