package com.deis.flightbase.util.exception_handler;

public class IllegalFlightStatusException extends RuntimeException{
    public IllegalFlightStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
