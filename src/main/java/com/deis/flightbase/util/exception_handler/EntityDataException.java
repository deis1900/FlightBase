package com.deis.flightbase.util.exception_handler;

public class EntityDataException extends RuntimeException {
    public EntityDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
