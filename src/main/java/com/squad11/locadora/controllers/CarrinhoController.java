package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CreateItemCarrinhoDTO;
import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.dtos.ResponseCarrinhoDTO;
import com.squad11.locadora.entities.Carrinho;
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

    @PatchMapping("{carrinhoId}")
    public ResponseEntity<ResponseCarrinhoDTO> addAndUpdateCarro(
            @PathVariable Long carrinhoId,
            @RequestBody @Validated CreateItemCarrinhoDTO carrinhoCarroDTO
    ) {

        Carrinho carrinho = carrinhoService.addAndUpateCarro(carrinhoId, carrinhoCarroDTO);

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.ok(responseCarrinhoDTO);
    }

    @DeleteMapping("{carrinhoId}/{carroId}")
    public ResponseEntity<Void> deleteCarroCarrinho(
            @PathVariable Long carrinhoId,
            @PathVariable Long carroId
    ) {

        carrinhoService.deleteCarro(carrinhoId, carroId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{carrinhoId}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long carrinhoId) {

        carrinhoService.deleteCarrinho(carrinhoId);

        return ResponseEntity.noContent().build();
    }
}
