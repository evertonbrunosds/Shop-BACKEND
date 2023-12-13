package com.github.evertonbrunosds.shop.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.github.evertonbrunosds.shop.util.ResourceException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException() {
        final ResourceException resourceException = new ResourceException(NOT_IMPLEMENTED);
        return new ResponseEntity<>(resourceException.getReason(), resourceException.getInvolvedStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException() {
        final ResourceException resourceException = new ResourceException(INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(resourceException.getReason(), resourceException.getInvolvedStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException() {
        final ResourceException resourceException = new ResourceException(BAD_REQUEST);
        return new ResponseEntity<>(resourceException.getReason(), resourceException.getInvolvedStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceException.class)
    public ResponseEntity<?> handleResourceException(final ResourceException resourceException) {
        return new ResponseEntity<>(resourceException.getReason(), resourceException.getInvolvedStatus());
    }

}
