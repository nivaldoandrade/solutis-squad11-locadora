package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.dtos.pessoa.MotoristaDTO;
import com.squad11.locadora.entities.aluguel.Carrinho;
import com.squad11.locadora.entities.aluguel.ItemCarrinho;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CarrinhoDTO(
        Long id,

        MotoristaDTO motorista,

        List<ItemCarrinhoDTO> carros,

        BigDecimal total
) {

    public static CarrinhoDTO from(Carrinho carrinho) {
        List<ItemCarrinhoDTO> carros = carrinho.getItemCarrinhos()
                .stream().map(ItemCarrinhoDTO::from).toList();

        BigDecimal total = carrinho.getItemCarrinhos()
                .stream()
                .map(ItemCarrinho::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return  CarrinhoDTO.builder()
                .id(carrinho.getId())
                .motorista(MotoristaDTO.from(carrinho.getMotorista()))
                .carros(carros)
                .total(total)
                .build();

    }

}
