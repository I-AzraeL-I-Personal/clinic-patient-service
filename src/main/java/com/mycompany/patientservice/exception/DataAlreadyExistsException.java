package com.mycompany.patientservice.exception;

public class DataAlreadyExistsException extends RuntimeException {

    public DataAlreadyExistsException(String resource, String message) {
        super(String.format("Failed for [%s]: %s", resource, message));
    }
}
