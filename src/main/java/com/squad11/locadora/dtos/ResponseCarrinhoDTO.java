package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Carrinho;
import com.squad11.locadora.entities.ItemCarrinho;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ResponseCarrinhoDTO(
        Long id,

        MotoristaDTO motorista,

        List<ResponseitemCarrinhoDTO> carros,

        BigDecimal total
) {

    public static ResponseCarrinhoDTO from(Carrinho carrinho) {
        List<ResponseitemCarrinhoDTO> carros = carrinho.getItemCarrinhos()
                .stream().map(ResponseitemCarrinhoDTO::from).toList();

        BigDecimal total = carrinho.getItemCarrinhos()
                .stream()
                .map(ItemCarrinho::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return  ResponseCarrinhoDTO.builder()
                .id(carrinho.getId())
                .motorista(MotoristaDTO.from(carrinho.getMotorista()))
                .carros(carros)
                .total(total)
                .build();

    }

}
