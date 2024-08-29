package com.squad11.locadora.exceptions;

public class EmailAlreadyInUseException extends FieldAlreadyInUseException {

    public EmailAlreadyInUseException() {
        super("Email is already in use");
    }
}
