package com.squad11.locadora.dtos.carro.request;

import com.squad11.locadora.constraints.EnumCategoriaModeloPattern;
import com.squad11.locadora.entities.enums.CategoriaEnum;
import com.squad11.locadora.entities.carro.Fabricante;
import com.squad11.locadora.entities.carro.ModeloCarro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateModeloCarroDTO(

        @NotBlank(message = "Descrição é obrigátorio")
        String descricao,

        @NotNull(message = "O ID do fabricante é obrigátorio")
        Long fabricanteId,

        @EnumCategoriaModeloPattern
        String categoria
) {

        public static ModeloCarro to(CreateModeloCarroDTO createModeloCarroDTO, Fabricante fabricante) {
                return new ModeloCarro(
                        createModeloCarroDTO.descricao,
                        CategoriaEnum.fromString(createModeloCarroDTO.categoria),
                        fabricante
                );
        }
}
