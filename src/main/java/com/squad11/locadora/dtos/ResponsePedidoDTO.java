package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Pedido;
import com.squad11.locadora.entities.StatusPedidoEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record ResponsePedidoDTO(
        Long id,

        List<ResponseAluguelDTO> alugueis,

        StatusPedidoEnum status
) {

    public static ResponsePedidoDTO from(Pedido pedido) {
        List<ResponseAluguelDTO> responseAluguelDTOS = pedido.getAlugueis()
                .stream()
                .map(ResponseAluguelDTO::from)
                .toList();

        return ResponsePedidoDTO.builder()
                .id(pedido.getId())
                .alugueis(responseAluguelDTOS)
                .status(pedido.getStatus())
                .build();
    }
}
