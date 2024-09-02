package com.squad11.locadora.dtos.aluguel.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record CreateAluguelDTO(
        @NotNull(message = "O aceitouTermos é obrigátorio")
        @AssertTrue(message = "O aceitouTermos é obrigátorio ser TRUE")
        Boolean aceitouTermos
) {
}
