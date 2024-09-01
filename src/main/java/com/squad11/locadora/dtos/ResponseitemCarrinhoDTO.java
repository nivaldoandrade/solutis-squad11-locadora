package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.ItemCarrinho;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ResponseitemCarrinhoDTO(

        LocalDate dataInicio,

        LocalDate dataTermino,

        CarroDTO carro,

        BigDecimal valorTotal,

        ApoliceDTO apolice
) {

    public static ResponseitemCarrinhoDTO from(ItemCarrinho itemCarrinho) {

        return ResponseitemCarrinhoDTO.builder()
                .dataInicio(itemCarrinho.getDataInicio())
                .dataTermino(itemCarrinho.getDataTermino())
                .carro( CarroDTO.from(itemCarrinho.getCarro()))
                .valorTotal(itemCarrinho.getValorTotal())
                .apolice(ApoliceDTO.from(itemCarrinho.getApolice()))
                .build();
    }
}
