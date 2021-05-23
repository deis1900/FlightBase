package com.deis.flightbase.util.exception_handler;

public class NoSuchElementFoundException extends RuntimeException {
    public NoSuchElementFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
