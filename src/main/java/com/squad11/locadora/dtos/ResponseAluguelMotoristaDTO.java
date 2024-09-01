package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.entities.StatusAluguelEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ResponseAluguelMotoristaDTO(
        Long id,

        LocalDate dataPedido,

        LocalDate dataEntrega,

        LocalDate dataDevolucao,

        BigDecimal valorTotal,

        StatusAluguelEnum status,

        Boolean termos,

        CarroDTO carro,

        ApoliceDTO apolice
) {

    public static ResponseAluguelMotoristaDTO from(Aluguel aluguel) {
        return ResponseAluguelMotoristaDTO.builder()
                .id(aluguel.getId())
                .dataPedido(aluguel.getDataPedido())
                .dataEntrega(aluguel.getDataEntrega())
                .dataDevolucao(aluguel.getDataDevolucao())
                .valorTotal(aluguel.getValorTotal())
                .status(aluguel.getStatus())
                .termos(aluguel.getAceitouTermos())
                .carro(CarroDTO.from(aluguel.getCarro()))
                .apolice(ApoliceDTO.from(aluguel.getApolice()))
                .build();
    }
}
