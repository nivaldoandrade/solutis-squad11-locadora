package com.squad11.locadora.controllers.impl;

import com.squad11.locadora.controllers.AcessorioController;
import com.squad11.locadora.dtos.AcessorioDTO;
import com.squad11.locadora.dtos.CreateAcessorioDTO;
import com.squad11.locadora.dtos.ListAcessorioDTO;
import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.services.AcessorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AcessorioControllerImpl implements AcessorioController {

    @Autowired
    AcessorioService acessorioService;

    @Override
    public ResponseEntity<ListAcessorioDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    ) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "descricao"));

        Page<Acessorio> acessorios = acessorioService.list(pageable);

        ListAcessorioDTO acessorioDTO = ListAcessorioDTO.from(acessorios);

        return ResponseEntity.ok(acessorioDTO);
    }

    @Override
    public ResponseEntity<AcessorioDTO> show(@PathVariable Long acessorioId) {
        Acessorio acessorio = acessorioService.show(acessorioId);

        AcessorioDTO acessorioDTO = AcessorioDTO.from(acessorio);

        return ResponseEntity.ok(acessorioDTO);
    }

    @Override
    public ResponseEntity<AcessorioDTO> create(
            @RequestBody @Validated CreateAcessorioDTO createAcessorioDTO
    ) {

        Acessorio acessorio = acessorioService.create(createAcessorioDTO);

        AcessorioDTO acessorioDTO = AcessorioDTO.from(acessorio);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(acessorio.getId()).toUri();

        return ResponseEntity.created(uri).body(acessorioDTO);
    }
}
