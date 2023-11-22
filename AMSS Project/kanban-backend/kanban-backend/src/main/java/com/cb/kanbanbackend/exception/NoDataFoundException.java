package com.cb.kanbanbackend.exception;

// In case no data is found

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
