package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Fabricante;
import jakarta.validation.constraints.NotBlank;

public record CreateFabricanteDTO(

        @NotBlank(message = "Nome é obrigátorio")
        String nome
) {
        public static Fabricante to(CreateFabricanteDTO createFabricanteDTO) {
                return new Fabricante(createFabricanteDTO.nome);
        }
}
