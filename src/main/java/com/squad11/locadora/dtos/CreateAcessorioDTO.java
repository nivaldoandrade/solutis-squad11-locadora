package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Acessorio;
import jakarta.validation.constraints.NotBlank;

public record CreateAcessorioDTO(

        @NotBlank(message = "Descrição é obrigátorio")
        String descricao
) {
        public static Acessorio to(CreateAcessorioDTO createAcessorioDTO) {
                return new Acessorio(createAcessorioDTO.descricao);
        }
}
