package com.squad11.locadora.exceptions;

public class MakerNotFoundException extends EntityNotFoundException {
    public MakerNotFoundException() {
        super("O fabricante não existe");
    }
}
