package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CreateItemCarrinhoDTO;
import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.dtos.ResponseCarrinhoDTO;
import com.squad11.locadora.entities.Carrinho;
import com.squad11.locadora.repositories.CarrinhoRepository;
import com.squad11.locadora.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarrinhoDTO> show(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.showCarrinhoById(id);

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
            @RequestBody @Validated CreateItemCarrinhoDTO carrinhoCarroDTO
    ) {

        Carrinho carrinho = carrinhoService.addAndUpateCarro(id, carrinhoCarroDTO);

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.ok(responseCarrinhoDTO);
    }

    @DeleteMapping("{id}/{carroId}")
    public ResponseEntity<Void> deleteCarroCarrinho(
            @PathVariable Long id,
            @PathVariable Long carroId
    ) {

        carrinhoService.deleteCarro(id, carroId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long id) {

        carrinhoService.deleteCarrinho(id);

        return ResponseEntity.noContent().build();
    }
}
