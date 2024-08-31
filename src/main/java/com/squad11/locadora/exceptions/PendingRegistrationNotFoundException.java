package com.squad11.locadora.exceptions;

public class PendingRegistrationNotFoundException extends EntityNotFoundException {
    public PendingRegistrationNotFoundException() {
        super("Cadastro pendente não existe");
    }
}
