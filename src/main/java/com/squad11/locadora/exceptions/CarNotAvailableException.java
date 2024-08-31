package com.squad11.locadora.exceptions;

public class CarNotAvailableException extends FieldAlreadyInUseException {

    public CarNotAvailableException() {
        super("Este carro não está disponível no momento");
    }
}
