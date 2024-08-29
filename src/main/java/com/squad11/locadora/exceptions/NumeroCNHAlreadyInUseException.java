package com.squad11.locadora.exceptions;

public class NumeroCNHAlreadyInUseException extends FieldAlreadyInUseException {

    public NumeroCNHAlreadyInUseException() {
        super("Número CNH is already in use");
    }
}
