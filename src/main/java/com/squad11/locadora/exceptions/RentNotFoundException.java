package com.squad11.locadora.exceptions;

public class RentNotFoundException extends EntityNotFoundException {
    public RentNotFoundException() {
        super("Aluguel não existe");
    }
}
