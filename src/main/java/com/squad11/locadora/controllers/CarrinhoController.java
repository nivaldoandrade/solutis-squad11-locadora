package com.squad11.locadora.controllers;

import com.squad11.locadora.constraints.DataDevolucaoAfterDataEntregaPattern;
import com.squad11.locadora.dtos.CreateCarrinhoCarroDTO;
import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.dtos.ResponseCarrinhoDTO;
import com.squad11.locadora.entities.Carrinho;
import com.squad11.locadora.entities.CarrinhoCarro;
import com.squad11.locadora.repositories.CarrinhoRepository;
import com.squad11.locadora.services.CarrinhoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarrinhoDTO> show(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.show(id);

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.ok().body(responseCarrinhoDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseCarrinhoDTO> create(@RequestBody @Validated CreateCarrinhoDTO createCarrinhoDTO) {

        Carrinho carrinho = carrinhoService.create(createCarrinhoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(carrinho.getId()).toUri();

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.created(uri).body(responseCarrinhoDTO);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseCarrinhoDTO> createAndUpdateCarro(
            @PathVariable Long id,
            @RequestBody @Validated CreateCarrinhoCarroDTO carrinhoCarroDTO
    ) {

        Carrinho carrinho = carrinhoService.createAndUpateCarro(id, carrinhoCarroDTO);

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.ok(responseCarrinhoDTO);
    }

    @DeleteMapping("{id}/{carroId}")
    public ResponseEntity<Void> deleteCarroCarrinho(
            @PathVariable Long id,
            @PathVariable Long carroId
    ) {

        carrinhoService.deleteCarroCarrinho(id, carroId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long id) {

        carrinhoService.deleteCarrinho(id);

        return ResponseEntity.noContent().build();
    }
}
