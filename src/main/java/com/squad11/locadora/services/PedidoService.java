package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateAluguelDTO;
import com.squad11.locadora.entities.Pedido;

public interface PedidoService {

    Pedido show(Long pedidoId);

    Pedido payment(Long pedidoId);

    Pedido findById(Long pedidoId);

    Pedido create(Long carrinhoId, CreateAluguelDTO createAluguelDTO);
}
