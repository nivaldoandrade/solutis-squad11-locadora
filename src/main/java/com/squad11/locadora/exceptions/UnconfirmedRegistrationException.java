package com.squad11.locadora.exceptions;

public class UnconfirmedRegistrationException extends RuntimeException {

    public UnconfirmedRegistrationException() {
        super(
                "Não foi possível adicionar o carro ao carrinho. " +
                "Verifique seu e-mail e confirme seu cadastro para continuar."
        );
    }
}
