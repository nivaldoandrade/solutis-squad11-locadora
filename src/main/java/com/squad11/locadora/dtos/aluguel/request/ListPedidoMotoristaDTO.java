package com.squad11.locadora.dtos.aluguel.request;

import com.squad11.locadora.dtos.aluguel.PedidoMotoristaDTO;
import com.squad11.locadora.entities.aluguel.Pedido;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListPedidoMotoristaDTO(
        List<PedidoMotoristaDTO> pedidos,
        Long totalItems,
        Integer totalPages
) {

    public static ListPedidoMotoristaDTO from(Page<Pedido> pagePedidos) {
        List<PedidoMotoristaDTO> listPedidoMotoristaDTOS = pagePedidos.stream()
                .map(PedidoMotoristaDTO::from).toList();

        return new ListPedidoMotoristaDTO(
                listPedidoMotoristaDTOS,
                pagePedidos.getTotalElements(),
                pagePedidos.getTotalPages()
        );
    }
}
