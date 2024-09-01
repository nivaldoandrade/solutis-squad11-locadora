package com.squad11.locadora.exceptions;

public class RentalAlreadyPaidException extends RuntimeException {

    public RentalAlreadyPaidException() {
        super("O pagamento deste aluguel já foi realizado");
    }
}
