package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.CarrinhoCarro;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
public record ResponseCarrinhoCarroDTO(

        LocalDate dataInicio,

        LocalDate dataTermino,

        CarroDTO carro,

        BigDecimal valorTotal
) {

    public static ResponseCarrinhoCarroDTO from(CarrinhoCarro carrinhoCarro) {

        return ResponseCarrinhoCarroDTO.builder()
                .dataInicio(carrinhoCarro.getDataInicio())
                .dataTermino(carrinhoCarro.getDataTermino())
                .carro( CarroDTO.from(carrinhoCarro.getCarro()))
                .valorTotal(carrinhoCarro.getValorTotal())
                .build();
    }
}
