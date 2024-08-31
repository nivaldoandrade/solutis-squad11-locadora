package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Carrinho;
import com.squad11.locadora.entities.CarrinhoCarro;
import com.squad11.locadora.entities.Carro;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ResponseCarrinhoDTO(
        Long id,

        MotoristaDTO motorista,

        List<ResponseCarrinhoCarroDTO> carros,

        BigDecimal total
) {

    public static ResponseCarrinhoDTO from(Carrinho carrinho) {
        List<ResponseCarrinhoCarroDTO> carros = carrinho.getCarrinhoCarros()
                .stream().map(ResponseCarrinhoCarroDTO::from).toList();

        BigDecimal total = carrinho.getCarrinhoCarros()
                .stream()
                .map(CarrinhoCarro::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return  ResponseCarrinhoDTO.builder()
                .id(carrinho.getId())
                .motorista(MotoristaDTO.from(carrinho.getMotorista()))
                .carros(carros)
                .total(total)
                .build();

    }

}
