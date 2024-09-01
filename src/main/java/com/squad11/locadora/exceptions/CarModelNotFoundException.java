package com.squad11.locadora.exceptions;

public class CarModelNotFoundException extends EntityNotFoundException {
    public CarModelNotFoundException() {
        super("O Modelo do carro não existe");
    }
}
