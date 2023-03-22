package com.sg.vendingMachine.service;

public class ClassInsufficientFundsException extends Exception {

    public ClassInsufficientFundsException(String message) {
        super(message);
    }

    public ClassInsufficientFundsException(String message,
                                         Throwable cause) {
        super(message, cause);
    }
}
