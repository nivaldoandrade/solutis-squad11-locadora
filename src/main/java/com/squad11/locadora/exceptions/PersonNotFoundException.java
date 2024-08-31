package com.squad11.locadora.exceptions;

public class PersonNotFoundException extends EntityNotFoundException {
    public PersonNotFoundException() {
        super("Pessoa não existe");
    }
}
