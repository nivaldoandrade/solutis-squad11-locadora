package com.squad11.locadora.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException() {
        super("Email is already in use");
    }
}
