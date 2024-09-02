package com.squad11.locadora.dtos.aluguel.request;

import com.squad11.locadora.constraints.DataDevolucaoAfterDataEntregaPattern;
import com.squad11.locadora.constraints.DateFormatPattern;
import jakarta.validation.constraints.NotNull;

@DataDevolucaoAfterDataEntregaPattern
public record CreateItemCarrinhoDTO(

        @DateFormatPattern(message = "Data da inicio inválida. EX: dd-mm-yyyy")
        String dataInicio,

        @DateFormatPattern(message = "Data da término inválida. EX: dd-mm-yyyy")
        String dataTermino,

        @NotNull(message = "O ID do carro é obrigátorio")
        Long carroId,

        @NotNull(message = "O ID da apólice é obrigátorio")
        Long apoliceId
) {
}
