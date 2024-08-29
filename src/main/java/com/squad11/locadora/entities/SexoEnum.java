package com.squad11.locadora.entities;

public enum SexoEnum {
    MASCULINO,
    FEMININO;

    public static SexoEnum fromString(String value) {
        if(value !=null) {
            for(SexoEnum role: SexoEnum.values()) {
                if(value.equalsIgnoreCase(role.name())) {
                    return role;
                }
            }
        }

        throw new IllegalArgumentException("No constant with name " + value + " found");
    }
}
