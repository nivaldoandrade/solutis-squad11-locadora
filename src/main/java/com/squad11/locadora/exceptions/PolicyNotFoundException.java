package com.squad11.locadora.exceptions;

public class PolicyNotFoundException extends EntityNotFoundException {
    public PolicyNotFoundException() {
        super("Apólice não existe");
    }
}
