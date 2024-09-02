package com.squad11.locadora.dtos.aluguel.request;

import com.squad11.locadora.entities.aluguel.Apolice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateApoliceSeguroDTO(

        @Positive(message = "O valor da franquia tem que ser maior que 0")
        @NotNull(message = "O Valor da franquia é obrigátorio")
        BigDecimal valorFranquia,

        @NotNull(message = "O valor tem que ser TRUE ou FALSE")
        Boolean protecaoTerceiro,
        @NotNull(message = "O valor tem que ser TRUE ou FALSE")
        Boolean protecaoCausasNaturais,

        @NotNull(message = "O valor tem que ser TRUE ou FALSE")
        Boolean protecaoRoubo

) {
        public static Apolice to(CreateApoliceSeguroDTO createApoliceSeguroDTO) {
                return new Apolice(
                        createApoliceSeguroDTO.valorFranquia,
                        createApoliceSeguroDTO.protecaoTerceiro,
                        createApoliceSeguroDTO.protecaoCausasNaturais,
                        createApoliceSeguroDTO.protecaoRoubo
                );
        }
}
