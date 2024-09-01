package com.squad11.locadora.exceptions;

public class OrderNotFoundException extends EntityNotFoundException {
    public OrderNotFoundException() {
        super("O pedido não existe");
    }
}
