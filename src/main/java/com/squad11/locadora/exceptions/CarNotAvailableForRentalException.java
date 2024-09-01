package com.squad11.locadora.exceptions;

public class CarNotAvailableForRentalException extends FieldAlreadyInUseException {

    public CarNotAvailableForRentalException(Long id) {
        super(String.format("O carro com %d não está mais disponível para aluguel", id));
    }
}
