package com.squad11.locadora.exceptions;

public class CarNotAvailableForRentalException extends FieldAlreadyInUseException {

    public CarNotAvailableForRentalException() {
        super("Este carro não está mais disponível para aluguel");
    }
}
