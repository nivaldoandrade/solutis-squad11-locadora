package com.squad11.locadora.controllers;


import com.squad11.locadora.dtos.CreateAluguelDTO;
import com.squad11.locadora.dtos.ResponsePedidoDTO;
import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.entities.Motorista;
import com.squad11.locadora.entities.Pedido;
import com.squad11.locadora.repositories.PedidoRepository;
import com.squad11.locadora.services.AluguelService;
import com.squad11.locadora.services.MotoristaService;
import com.squad11.locadora.services.PedidoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    AluguelService aluguelService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @GetMapping("{pedidoId}")
    public ResponseEntity<ResponsePedidoDTO> show(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.show(pedidoId);

        ResponsePedidoDTO responsePedidoDTO = ResponsePedidoDTO.from(pedido);

        return ResponseEntity.ok(responsePedidoDTO);
    }

    @PostMapping("{carrinhoId}")
    public ResponseEntity<ResponsePedidoDTO> create(
            @PathVariable Long carrinhoId,
            @RequestBody @Validated CreateAluguelDTO createAluguelDTO
    ) {

        Pedido pedido = pedidoService.create(carrinhoId, createAluguelDTO);

        ResponsePedidoDTO responsePedidoDTO = ResponsePedidoDTO.from(pedido);

        return ResponseEntity.ok(responsePedidoDTO);
    }


    @PostMapping("{pedidoId}/pagamento-cartao")
    public ResponseEntity<ResponsePedidoDTO> paymentCard(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.payment(pedidoId);

        ResponsePedidoDTO responsePedidoDTO = ResponsePedidoDTO.from(pedido);

        return ResponseEntity.ok(responsePedidoDTO);
    }

}
