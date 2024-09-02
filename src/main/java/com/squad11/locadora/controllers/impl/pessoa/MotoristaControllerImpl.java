package com.squad11.locadora.controllers.impl.pessoa;

import com.squad11.locadora.controllers.pessoa.MotoristaController;

import com.squad11.locadora.dtos.aluguel.request.ListPedidoMotoristaDTO;
import com.squad11.locadora.dtos.pessoa.MotoristaDTO;
import com.squad11.locadora.dtos.pessoa.request.CreateLinkMotoristaDTO;
import com.squad11.locadora.dtos.pessoa.request.CreateMotoristaDTO;
import com.squad11.locadora.dtos.aluguel.request.ListAlugueisMotoristaDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.pessoa.Motorista;
import com.squad11.locadora.services.pessoa.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class MotoristaControllerImpl implements MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @Override
    public ResponseEntity<ListAlugueisMotoristaDTO> showAlugueis(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @PathVariable Long motoristaId
    ) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "dataPedido"));

        Page<Aluguel> alugueis = motoristaService.listAlugueis(pageable,motoristaId);

        ListAlugueisMotoristaDTO aluguelMotoristaDTOS = ListAlugueisMotoristaDTO.from(alugueis);

        return ResponseEntity.ok().body(aluguelMotoristaDTOS);
    }

    @Override
    public ResponseEntity<ListPedidoMotoristaDTO> showPedidos(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @PathVariable Long motoristaId
    ) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "id"));

        Page<Pedido> pedidos = motoristaService.listPedidos(pageable,motoristaId);

        ListPedidoMotoristaDTO listPedidoMotoristaDTO = ListPedidoMotoristaDTO.from(pedidos);

        return ResponseEntity.ok().body(listPedidoMotoristaDTO);
    }

    @Override
    public ResponseEntity<MotoristaDTO> show(@PathVariable Long motoristaId) {
        Motorista motorista = motoristaService.show(motoristaId);

        MotoristaDTO motoristaDTO = MotoristaDTO.from(motorista);

        return ResponseEntity.ok(motoristaDTO);
    }

    @Override
    public ResponseEntity<CreateLinkMotoristaDTO> create(@RequestBody CreateMotoristaDTO createMotoristaDTO) {

        String token = motoristaService.create(createMotoristaDTO);

        URI linkConfirmacaoURI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/confirmar-cadastro/")
                .path(token)
                .build().toUri();

        CreateLinkMotoristaDTO motoristaDTO = CreateLinkMotoristaDTO.from(linkConfirmacaoURI);

        return ResponseEntity.created(linkConfirmacaoURI).body(motoristaDTO);
    }

}
