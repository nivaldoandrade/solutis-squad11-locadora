package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CarroDTO;
import com.squad11.locadora.dtos.CreateCarroDTO;
import com.squad11.locadora.dtos.ListCarroDTO;
import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.services.CarroService;
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
import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/disponiveis")
    public ResponseEntity<ListCarroDTO> getCarrosDisponiveis(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @RequestParam(name = "categorias",required = false) List<String> categorias,
            @RequestParam(name = "acessorios", required = false) List<String> acessorios
    ) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "modelo.descricao"));

        Page<Carro> carros = carroService.list(pageable, categorias, acessorios);

        ListCarroDTO listCarroDTO = ListCarroDTO.from(carros);

        return ResponseEntity.ok().body(listCarroDTO);
    }

    @GetMapping("/disponiveis/{id}")
    public ResponseEntity<CarroDTO> show(@PathVariable Long id) {
        Carro carro = carroService.findByIdDisponivel(id);

        CarroDTO carroDTO = CarroDTO.from(carro);

        return ResponseEntity.ok(carroDTO);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Validated CreateCarroDTO createCarroDTO
    ) {

        Carro carro = carroService.create(createCarroDTO);

        CarroDTO carroDTO = CarroDTO.from(carro);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(carro.getId()).toUri();

        return ResponseEntity.created(uri).body(carroDTO);
    }



}
