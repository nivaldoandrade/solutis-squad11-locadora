package com.squad11.locadora.controllers;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/disponiveis")
    public ResponseEntity<?> getCarrosDisponiveis(
            @RequestParam(required = false) List<String> categorias
    ) {

        List<Carro> carros = carroService.findAll(categorias);

        return ResponseEntity.ok().body(carros);
    }

    @GetMapping("/disponiveis/{id}")
    public ResponseEntity<Carro> getCarroDetalhado(@PathVariable Long id) {
        return ResponseEntity.ok(carroService.findById(id));
    }

}
