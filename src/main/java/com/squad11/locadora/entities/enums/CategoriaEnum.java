package com.squad11.locadora.entities.enums;

public enum CategoriaEnum {

    HATCH_COMPACTO, HATCH_MEDIO, SEDAN_COMPACTO, SEDAN_MEDIO, SEDAN_GRANDE,
    MINIVAN, ESPORTIVO, UTILITARIO_COMERCIAL;

    public static CategoriaEnum fromString(String value) {
        if(value !=null) {
            for(CategoriaEnum categoria: CategoriaEnum.values()) {
                if(value.equalsIgnoreCase(categoria.name())) {
                    return categoria;
                }
            }
        }

        throw new IllegalArgumentException("A categoria " + value + " não é válida");
    }

}
