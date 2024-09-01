package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.ApoliceDTO;
import com.squad11.locadora.dtos.CreateApoliceSeguroDTO;
import com.squad11.locadora.dtos.ListApoliceSeguroDTO;
import com.squad11.locadora.entities.Apolice;
import com.squad11.locadora.services.ApoliceSeguroService;
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
@RequestMapping("/api/apolices-seguros")
public class ApoliceSeguroController {

    @Autowired
    ApoliceSeguroService apoliceSeguroService;

    @GetMapping
    public ResponseEntity<ListApoliceSeguroDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    ) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "valorFranquia"));

        Page<Apolice> apolices = apoliceSeguroService.list(pageable);

        ListApoliceSeguroDTO listApoliceSeguroDTO = ListApoliceSeguroDTO.from(apolices);

        return ResponseEntity.ok(listApoliceSeguroDTO);
    }

    @GetMapping("{apoliceSeguroId}")
    public ResponseEntity<ApoliceDTO> show(@PathVariable Long apoliceSeguroId) {
        Apolice apolice = apoliceSeguroService.show(apoliceSeguroId);

        ApoliceDTO apoliceDTO = ApoliceDTO.from(apolice);

        return ResponseEntity.ok(apoliceDTO);
    }

    @PostMapping
    public ResponseEntity<ApoliceDTO> create(
            @RequestBody @Validated CreateApoliceSeguroDTO createApoliceSeguroDTO
    ) {

        Apolice apolice = apoliceSeguroService.create(createApoliceSeguroDTO);

        ApoliceDTO apoliceDTO = ApoliceDTO.from(apolice);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(apolice.getId()).toUri();

        return ResponseEntity.created(uri).body(apoliceDTO);
    }
}
