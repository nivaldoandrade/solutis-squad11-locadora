package com.squad11.locadora.dtos.carro;

import com.squad11.locadora.entities.enums.CategoriaEnum;
import com.squad11.locadora.entities.carro.ModeloCarro;
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
