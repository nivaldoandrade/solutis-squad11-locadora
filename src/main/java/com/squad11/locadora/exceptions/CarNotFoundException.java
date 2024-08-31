package com.squad11.locadora.exceptions;

public class CarNotFoundException extends EntityNotFoundException {
    public CarNotFoundException() {
        super("Carro não existe");
    }
}
