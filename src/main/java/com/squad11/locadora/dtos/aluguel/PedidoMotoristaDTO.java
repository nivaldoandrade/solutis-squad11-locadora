package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.enums.StatusPedidoEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record PedidoMotoristaDTO(
        Long id,

        StatusPedidoEnum status,

        List<AluguelMotoristaDTO> alugueis
) {

    public static PedidoMotoristaDTO from(Pedido pedido) {

        List<AluguelMotoristaDTO> aluguelMotoristaDTOS = pedido.getAlugueis()
                .stream().map(AluguelMotoristaDTO::from).toList();

        return PedidoMotoristaDTO.builder()
                .id(pedido.getId())
                .status(pedido.getStatus())
                .alugueis(aluguelMotoristaDTOS)
                .build();
    }
}
