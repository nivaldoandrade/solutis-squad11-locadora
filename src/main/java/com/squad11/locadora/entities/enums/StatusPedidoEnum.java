package com.squad11.locadora.entities.enums;

public enum StatusPedidoEnum {

    PENDENTE,

    CONCLUIDO;

    public static StatusPedidoEnum fromString(String value) {
        if(value !=null) {
            for(StatusPedidoEnum categoria: StatusPedidoEnum.values()) {
                if(value.equalsIgnoreCase(categoria.name())) {
                    return categoria;
                }
            }
        }

        throw new IllegalArgumentException("O status do carro " + value + " não é válida");
    }

}
