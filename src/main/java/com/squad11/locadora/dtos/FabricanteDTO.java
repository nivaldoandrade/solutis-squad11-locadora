package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Fabricante;
import lombok.Builder;

@Builder
public record FabricanteDTO(
        Long id,

        String nome
) {

    public static FabricanteDTO from(Fabricante fabricante) {
        return FabricanteDTO.builder()
                .id(fabricante.getId())
                .nome(fabricante.getNome())
                .build();
    }
}
