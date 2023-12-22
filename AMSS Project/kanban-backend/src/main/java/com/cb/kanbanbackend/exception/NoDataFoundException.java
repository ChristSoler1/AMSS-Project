package com.cb.kanbanbackend.exception;

//Falls keine Daten vorhanden/gefunden werden.
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
