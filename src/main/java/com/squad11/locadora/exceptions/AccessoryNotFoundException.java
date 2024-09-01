package com.squad11.locadora.exceptions;

public class AccessoryNotFoundException extends EntityNotFoundException {
    public AccessoryNotFoundException() {
        super("O Acessório não existe");
    }
}
