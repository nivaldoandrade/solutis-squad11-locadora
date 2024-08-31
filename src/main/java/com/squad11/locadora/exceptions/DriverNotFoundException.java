package com.squad11.locadora.exceptions;

public class DriverNotFoundException extends EntityNotFoundException {
    public DriverNotFoundException() {
        super("Motorista não existe");
    }
}
