package com.squad11.locadora.services.aluguel;

import com.squad11.locadora.dtos.aluguel.request.CreateAluguelDTO;
import com.squad11.locadora.entities.aluguel.Pedido;

public interface PedidoService {

    Pedido show(Long pedidoId);

    Pedido payment(Long pedidoId);

    Pedido findById(Long pedidoId);

    Pedido create(Long carrinhoId, CreateAluguelDTO createAluguelDTO);
}
