package com.squad11.locadora.entities;

public enum StatusAluguelEnum {
    PENDENTE,

    CONCLUIDO;

    public static StatusAluguelEnum fromString(String value) {
        if(value !=null) {
            for(StatusAluguelEnum categoria: StatusAluguelEnum.values()) {
                if(value.equalsIgnoreCase(categoria.name())) {
                    return categoria;
                }
            }
        }

        throw new IllegalArgumentException("A categoria " + value + " não é válida");
    }

}
