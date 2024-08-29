package com.squad11.locadora.exceptions;

public class CPFAlreadyInUseException extends FieldAlreadyInUseException {

    public CPFAlreadyInUseException() {
        super("CPF is already in use");
    }
}
