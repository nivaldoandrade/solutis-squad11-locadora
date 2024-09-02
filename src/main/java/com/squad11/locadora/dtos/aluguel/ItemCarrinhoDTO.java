package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.dtos.carro.CarroDTO;
import com.squad11.locadora.entities.aluguel.ItemCarrinho;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ItemCarrinhoDTO(

        LocalDate dataInicio,

        LocalDate dataTermino,

        CarroDTO carro,

        BigDecimal valorTotal,

        ApoliceDTO apolice
) {

    public static ItemCarrinhoDTO from(ItemCarrinho itemCarrinho) {

        return ItemCarrinhoDTO.builder()
                .dataInicio(itemCarrinho.getDataInicio())
                .dataTermino(itemCarrinho.getDataTermino())
                .carro( CarroDTO.from(itemCarrinho.getCarro()))
                .valorTotal(itemCarrinho.getValorTotal())
                .apolice(ApoliceDTO.from(itemCarrinho.getApolice()))
                .build();
    }
}
