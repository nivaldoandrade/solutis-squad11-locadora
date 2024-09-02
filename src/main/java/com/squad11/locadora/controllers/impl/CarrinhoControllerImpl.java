package com.squad11.locadora.controllers.impl;

import com.squad11.locadora.controllers.CarrinhoController;
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
public class CarrinhoControllerImpl implements CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @Override
    public ResponseEntity<ResponseCarrinhoDTO> show(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.showCarrinhoById(id);

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.ok().body(responseCarrinhoDTO);
    }

    @Override
    public ResponseEntity<ResponseCarrinhoDTO> create(
            @RequestBody @Validated CreateCarrinhoDTO createCarrinhoDTO
    ) {

        Carrinho carrinho = carrinhoService.create(createCarrinhoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(carrinho.getId()).toUri();

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.created(uri).body(responseCarrinhoDTO);
    }

    @Override
    public ResponseEntity<ResponseCarrinhoDTO> addAndUpdateCarro(
            @PathVariable Long carrinhoId,
            @RequestBody @Validated CreateItemCarrinhoDTO carrinhoCarroDTO
    ) {

        Carrinho carrinho = carrinhoService.addAndUpateCarro(carrinhoId, carrinhoCarroDTO);

        ResponseCarrinhoDTO responseCarrinhoDTO = ResponseCarrinhoDTO.from(carrinho);

        return ResponseEntity.ok(responseCarrinhoDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCarroCarrinho(
            @PathVariable Long carrinhoId,
            @PathVariable Long carroId
    ) {

        carrinhoService.deleteCarro(carrinhoId, carroId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long carrinhoId) {

        carrinhoService.deleteCarrinho(carrinhoId);

        return ResponseEntity.noContent().build();
    }
}
