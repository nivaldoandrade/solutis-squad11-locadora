package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.CategoriaEnum;
import com.squad11.locadora.entities.Fabricante;
import com.squad11.locadora.entities.ModeloCarro;
import lombok.Builder;


@Builder
public record ModeloDTO(
        Long id,

        String descricao,

        CategoriaEnum categoria,

        FabricanteDTO fabricante
) {

    public static ModeloDTO from(ModeloCarro modelo) {
        return ModeloDTO.builder()
                .id(modelo.getId())
                .descricao(modelo.getDescricao())
                .categoria(modelo.getCategoria())
                .fabricante(FabricanteDTO.from(modelo.getFabricante()))
                .build();
    }
}
