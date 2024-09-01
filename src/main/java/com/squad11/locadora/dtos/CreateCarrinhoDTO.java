package com.squad11.locadora.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CreateCarrinhoDTO(
        @NotNull(message = "O ID do motorista é obrigátorio")
        Long motoristaId,

        @Valid CreateItemCarrinhoDTO carro
) {
}
