package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.enums.StatusPedidoEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record PedidoDTO(
        Long id,

        List<AluguelDTO> alugueis,

        StatusPedidoEnum status
) {

    public static PedidoDTO from(Pedido pedido) {
        List<AluguelDTO> aluguelDTOS = pedido.getAlugueis()
                .stream()
                .map(AluguelDTO::from)
                .toList();

        return PedidoDTO.builder()
                .id(pedido.getId())
                .alugueis(aluguelDTOS)
                .status(pedido.getStatus())
                .build();
    }
}
