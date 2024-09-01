package com.squad11.locadora.exceptions;

public class PersonAlreadyActiveException extends RuntimeException {
    public PersonAlreadyActiveException() {
        super("A pessoa já está ativo e não necessita de uma nova confirmação de cadastro");
    }
}
