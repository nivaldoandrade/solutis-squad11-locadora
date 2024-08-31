package com.squad11.locadora.exceptions;

public class CarInCartNotFoundException extends EntityNotFoundException {
    public CarInCartNotFoundException() {
        super("Carro não existe no carrinho");
    }
}
