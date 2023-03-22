package com.sg.vendingMachine.service;

public class ClassNoItemInventoryException extends Exception{

    public ClassNoItemInventoryException(String message) {
        super(message);
    }

    public ClassNoItemInventoryException(String message,
                                           Throwable cause) {
        super(message, cause);
    }


}
