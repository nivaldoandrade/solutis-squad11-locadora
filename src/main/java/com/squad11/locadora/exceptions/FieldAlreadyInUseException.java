package com.squad11.locadora.exceptions;

public class FieldAlreadyInUseException extends RuntimeException{

    public FieldAlreadyInUseException(String message) {
        super(message);
    }
}
