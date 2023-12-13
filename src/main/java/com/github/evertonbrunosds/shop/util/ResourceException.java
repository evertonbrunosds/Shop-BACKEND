package com.github.evertonbrunosds.shop.util;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceException extends RuntimeException {

    private final HttpStatus involvedStatus;

    private final Parameter involvedParameter;

    private final Class<?> involvedClass;

    public ResourceException(final HttpStatus involvedStatus) {
        this.involvedStatus = involvedStatus;
        this.involvedParameter = null;
        this.involvedClass = null;
    }

    public ResourceException(final HttpStatus involvedStatus, final Class<?> involvedClass) {
        this.involvedStatus = involvedStatus;
        this.involvedParameter = null;
        this.involvedClass = involvedClass;
    }

    public HttpStatus getInvolvedStatus() {
        return involvedStatus != null
                ? involvedStatus
                : INTERNAL_SERVER_ERROR;
    }

    public Parameter getInvolvedParameter() {
        return involvedParameter;
    }

    public Reason getReason() {
        return new Reason();
    }

    public class Reason {

        public String getStatusName() {
            return involvedStatus != null
                    ? involvedStatus.getReasonPhrase().toUpperCase().replace(" ", "_")
                    : INTERNAL_SERVER_ERROR.getReasonPhrase().toUpperCase().replace(" ", "_");
        }

        public String getStatusParameter() {
            return involvedParameter != null
                    ? involvedParameter.toString()
                    : "UNDETERMINED";
        }

        public String getStatusClass() {
            return involvedClass != null
                    ? involvedClass.getName()
                    : "UNDETERMINED";
        }

    }

}