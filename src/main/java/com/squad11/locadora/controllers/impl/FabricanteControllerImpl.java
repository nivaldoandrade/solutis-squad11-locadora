package com.squad11.locadora.controllers.impl;


import com.squad11.locadora.controllers.FabricanteController;
import com.squad11.locadora.dtos.CreateFabricanteDTO;
import com.squad11.locadora.dtos.FabricanteDTO;
import com.squad11.locadora.dtos.ListFabricanteDTO;
import com.squad11.locadora.entities.Fabricante;
import com.squad11.locadora.services.FabricanteService;
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

public class FabricanteControllerImpl implements FabricanteController {

    @Autowired
    FabricanteService fabricanteService;

    @Override
    public ResponseEntity<ListFabricanteDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    ) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "nome"));

        Page<Fabricante> fabricantes = fabricanteService.list(pageable);

        ListFabricanteDTO fabricanteDTO = ListFabricanteDTO.from(fabricantes);

        return ResponseEntity.ok(fabricanteDTO);
    }

    @Override
    public ResponseEntity<FabricanteDTO> show(@PathVariable Long fabricanteId) {
        Fabricante fabricante = fabricanteService.show(fabricanteId);

        FabricanteDTO fabricanteDTO = FabricanteDTO.from(fabricante);

        return ResponseEntity.ok(fabricanteDTO);
    }

    @Override
    public ResponseEntity<FabricanteDTO> create(
            @RequestBody @Validated CreateFabricanteDTO createFabricanteDTO
    ) {

        Fabricante fabricante = fabricanteService.create(createFabricanteDTO);

        FabricanteDTO fabricanteDTO = FabricanteDTO.from(fabricante);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(fabricante.getId()).toUri();

        return ResponseEntity.created(uri).body(fabricanteDTO);
    }
}
