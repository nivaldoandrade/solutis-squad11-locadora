package com.squad11.locadora.entities;

public enum StatusCarroEnum {

    DISPONIVEL,
    NO_CARRINHO,
    RESERVADO;

    public static StatusCarroEnum fromString(String value) {
        if(value !=null) {
            for(StatusCarroEnum categoria: StatusCarroEnum.values()) {
                if(value.equalsIgnoreCase(categoria.name())) {
                    return categoria;
                }
            }
        }

        throw new IllegalArgumentException("O status do carro " + value + " não é válida");
    }

}
