package com.squad11.locadora.controllers.impl;

import com.squad11.locadora.controllers.PedidoController;
import com.squad11.locadora.dtos.CreateAluguelDTO;
import com.squad11.locadora.dtos.ResponsePedidoDTO;
import com.squad11.locadora.entities.Pedido;
import com.squad11.locadora.repositories.PedidoRepository;
import com.squad11.locadora.services.AluguelService;
import com.squad11.locadora.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class PedidoControllerImpl implements PedidoController {

    @Autowired
    AluguelService aluguelService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @Override
    public ResponseEntity<ResponsePedidoDTO> show(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.show(pedidoId);

        ResponsePedidoDTO responsePedidoDTO = ResponsePedidoDTO.from(pedido);

        return ResponseEntity.ok(responsePedidoDTO);
    }

    @Override
    public ResponseEntity<ResponsePedidoDTO> create(
            @PathVariable Long carrinhoId,
            @RequestBody @Validated CreateAluguelDTO createAluguelDTO
    ) {

        Pedido pedido = pedidoService.create(carrinhoId, createAluguelDTO);

        ResponsePedidoDTO responsePedidoDTO = ResponsePedidoDTO.from(pedido);

        return ResponseEntity.ok(responsePedidoDTO);
    }


    @Override
    public ResponseEntity<ResponsePedidoDTO> paymentCard(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.payment(pedidoId);

        ResponsePedidoDTO responsePedidoDTO = ResponsePedidoDTO.from(pedido);

        return ResponseEntity.ok(responsePedidoDTO);
    }

}
