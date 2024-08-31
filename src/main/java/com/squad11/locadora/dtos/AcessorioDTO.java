package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Acessorio;
import lombok.Builder;

@Builder
public record AcessorioDTO(
        Long id,

        String descricao
) {

    public static AcessorioDTO from(Acessorio acessorio) {
        return AcessorioDTO.builder()
                .id(acessorio.getId())
                .descricao(acessorio.getDescricao())
                .build();
    }
}
