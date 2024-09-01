package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.*;

import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.entities.Motorista;
import com.squad11.locadora.services.MotoristaService;
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
@RequestMapping("/api/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @GetMapping("{motoristaId}/alugueis")
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

    @GetMapping("{motoristaId}")
    public ResponseEntity<MotoristaDTO> show(@PathVariable Long motoristaId) {
        Motorista motorista = motoristaService.show(motoristaId);

        MotoristaDTO motoristaDTO = MotoristaDTO.from(motorista);

        return ResponseEntity.ok(motoristaDTO);
    }

    @GetMapping("{motoristaId}/{aluguelId}")
    public ResponseEntity<ResponseAluguelMotoristaDTO> showAluguel(
            @PathVariable Long motoristaId,
            @PathVariable Long aluguelId
    ) {
        Aluguel aluguel = motoristaService.showAluguel(motoristaId, aluguelId);

        ResponseAluguelMotoristaDTO aluguelMotoristaDTO = ResponseAluguelMotoristaDTO.from(aluguel);

        return ResponseEntity.ok(aluguelMotoristaDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseCreateMotoristaDTO> create(@RequestBody CreateMotoristaDTO createMotoristaDTO) {

        String token = motoristaService.create(createMotoristaDTO);

        URI linkConfirmacaoURI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/confirmar-cadastro/")
                .path(token)
                .build().toUri();

        ResponseCreateMotoristaDTO motoristaDTO = ResponseCreateMotoristaDTO.from(linkConfirmacaoURI);

        return ResponseEntity.created(linkConfirmacaoURI).body(motoristaDTO);
    }
}
