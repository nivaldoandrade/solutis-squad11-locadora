package com.squad11.locadora.exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {

        super("O carrinho não possui nenhum carro");
    }
}
