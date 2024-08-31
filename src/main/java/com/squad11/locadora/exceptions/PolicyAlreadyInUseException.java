package com.squad11.locadora.exceptions;

public class PolicyAlreadyInUseException extends FieldAlreadyInUseException {

    public PolicyAlreadyInUseException() {
        super("Esta apólice já está em uso por outro carro");
    }
}
