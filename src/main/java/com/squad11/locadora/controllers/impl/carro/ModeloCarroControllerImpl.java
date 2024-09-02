package com.squad11.locadora.controllers.impl.carro;

import com.squad11.locadora.controllers.carro.ModeloCarroController;
import com.squad11.locadora.dtos.carro.ModeloDTO;
import com.squad11.locadora.dtos.carro.request.CreateModeloCarroDTO;
import com.squad11.locadora.dtos.carro.request.ListModeloCarroDTO;
import com.squad11.locadora.entities.carro.ModeloCarro;
import com.squad11.locadora.services.carro.ModeloCarroService;
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
public class ModeloCarroControllerImpl implements ModeloCarroController {

    @Autowired
    ModeloCarroService modeloCarroService;

    @Override
    public ResponseEntity<ListModeloCarroDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    ) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "descricao"));

        Page<ModeloCarro> modeloCarros = modeloCarroService.list(pageable);

        ListModeloCarroDTO listModeloCarroDTO = ListModeloCarroDTO.from(modeloCarros);


        return ResponseEntity.ok(listModeloCarroDTO);
    }

    @Override
    @GetMapping("{modeloCarroId}")
    public ResponseEntity<ModeloDTO> show(@PathVariable Long modeloCarroId) {
        ModeloCarro modeloCarro = modeloCarroService.show(modeloCarroId);

        ModeloDTO modeloDTO = ModeloDTO.from(modeloCarro);

        return ResponseEntity.ok(modeloDTO);
    }

    @Override
    @PostMapping
    public ResponseEntity<ModeloDTO> create(
            @RequestBody @Validated CreateModeloCarroDTO createModeloCarroDTO
    ) {

        ModeloCarro modeloCarro = modeloCarroService.create(createModeloCarroDTO);

        ModeloDTO modeloDTO = ModeloDTO.from(modeloCarro);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(modeloCarro.getId()).toUri();

        return ResponseEntity.created(uri).body(modeloDTO);
    }
}
