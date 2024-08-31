package com.squad11.locadora.exceptions;

public class CartNotFoundException extends EntityNotFoundException {
    public CartNotFoundException() {
        super("O carrinho não existe");
    }
}
