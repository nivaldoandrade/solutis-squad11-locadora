package com.squad11.locadora.exceptions;

public class PolicyAlreadyInUseException extends FieldAlreadyInUseException {

    public PolicyAlreadyInUseException(Long id) {
        super(String.format("A apólice com o id %d já está em uso por outro carro", id));
    }

}
