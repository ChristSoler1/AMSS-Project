package com.cb.kanbanbackend.exception;

// `CommonException` ist eine benutzerdefinierte unchecked Ausnahme, die von `RuntimeException` ableitet.
// Unchecked Ausnahmen werden normalerweise für programmatische Fehler verwendet.
public class CommonException extends RuntimeException {
    // Ein Konstruktor, der eine Nachricht akzeptiert. Diese Nachricht wird in der stack trace der Ausnahme angezeigt, wenn sie ausgelöst wird.
    public CommonException(String message) {
        // Die super-Methode ruft den entsprechenden Konstruktor der Basisklasse (RuntimeExpection) auf.
        super(message);
    }
}