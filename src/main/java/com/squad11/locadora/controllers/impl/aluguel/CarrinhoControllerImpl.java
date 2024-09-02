package com.squad11.locadora.controllers.impl.aluguel;

import com.squad11.locadora.controllers.aluguel.CarrinhoController;
import com.squad11.locadora.dtos.aluguel.request.CreateItemCarrinhoDTO;
import com.squad11.locadora.dtos.aluguel.request.CreateCarrinhoDTO;
import com.squad11.locadora.dtos.aluguel.CarrinhoDTO;
import com.squad11.locadora.entities.aluguel.Carrinho;
import com.squad11.locadora.services.aluguel.CarrinhoService;
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
    public ResponseEntity<CarrinhoDTO> show(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.showCarrinhoById(id);

        CarrinhoDTO carrinhoDTO = CarrinhoDTO.from(carrinho);

        return ResponseEntity.ok().body(carrinhoDTO);
    }

    @Override
    public ResponseEntity<CarrinhoDTO> create(
            @RequestBody @Validated CreateCarrinhoDTO createCarrinhoDTO
    ) {

        Carrinho carrinho = carrinhoService.create(createCarrinhoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(carrinho.getId()).toUri();

        CarrinhoDTO carrinhoDTO = CarrinhoDTO.from(carrinho);

        return ResponseEntity.created(uri).body(carrinhoDTO);
    }

    @Override
    public ResponseEntity<CarrinhoDTO> addAndUpdateCarro(
            @PathVariable Long carrinhoId,
            @RequestBody @Validated CreateItemCarrinhoDTO carrinhoCarroDTO
    ) {

        Carrinho carrinho = carrinhoService.addAndUpateCarro(carrinhoId, carrinhoCarroDTO);

        CarrinhoDTO carrinhoDTO = CarrinhoDTO.from(carrinho);

        return ResponseEntity.ok(carrinhoDTO);
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
