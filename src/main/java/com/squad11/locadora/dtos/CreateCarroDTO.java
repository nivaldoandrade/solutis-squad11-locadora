package com.squad11.locadora.dtos;

import com.squad11.locadora.constraints.StatusCarroEnumPattern;
import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.entities.ModeloCarro;
import com.squad11.locadora.entities.StatusCarroEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CreateCarroDTO(

        @NotBlank(message = "A Placa é obrigátorio")
        String placa,

        @NotBlank(message = "O Chassi é obrigátorio")
        String chassi,

        @NotBlank(message = "A Cor é obrigátorio")
        String cor,

        @NotNull(message = "O valor da diaria é obrigátorio")
        BigDecimal valorDiaria,

        List<Long> acessoriosId,

        @Schema(example = "DISPONIVEL ou RESERVADO")
        @StatusCarroEnumPattern
        String status,

        @NotNull(message = "O ID do modelo do carro é obrigátorio")
        Long modeloId
) {
    public static Carro to(
            CreateCarroDTO createCarroDTO,
            List<Acessorio> acessorios,
            ModeloCarro modeloCarro
    ) {
        return new Carro(
                createCarroDTO.chassi,
                createCarroDTO.placa,
                createCarroDTO.cor,
                createCarroDTO.valorDiaria,
                modeloCarro,
                StatusCarroEnum.fromString(createCarroDTO.status),
                acessorios
        );
    }
}
