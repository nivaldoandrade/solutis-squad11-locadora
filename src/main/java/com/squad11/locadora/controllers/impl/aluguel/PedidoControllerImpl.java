package com.squad11.locadora.controllers.impl.aluguel;

import com.squad11.locadora.controllers.aluguel.PedidoController;
import com.squad11.locadora.dtos.aluguel.request.CreateAluguelDTO;
import com.squad11.locadora.dtos.aluguel.PedidoDTO;
import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.repositories.aluguel.PedidoRepository;
import com.squad11.locadora.services.aluguel.AluguelService;
import com.squad11.locadora.services.aluguel.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
public class PedidoControllerImpl implements PedidoController {

    @Autowired
    AluguelService aluguelService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @Override
    public ResponseEntity<PedidoDTO> show(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.show(pedidoId);

        PedidoDTO pedidoDTO = PedidoDTO.from(pedido);

        return ResponseEntity.ok(pedidoDTO);
    }

    @Override
    public ResponseEntity<PedidoDTO> create(
            @PathVariable Long carrinhoId,
            @RequestBody @Validated CreateAluguelDTO createAluguelDTO
    ) {

        Pedido pedido = pedidoService.create(carrinhoId, createAluguelDTO);

        PedidoDTO pedidoDTO = PedidoDTO.from(pedido);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(pedidoDTO);
    }


    @Override
    public ResponseEntity<PedidoDTO> paymentCard(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.payment(pedidoId);

        PedidoDTO pedidoDTO = PedidoDTO.from(pedido);

        return ResponseEntity.ok(pedidoDTO);
    }

}
