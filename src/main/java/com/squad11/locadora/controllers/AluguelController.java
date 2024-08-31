package com.squad11.locadora.controllers;

import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.services.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    @Autowired
    AluguelService aluguelService;

    @PostMapping("{carrinhoId}")
    public ResponseEntity<?> create(@PathVariable Long carrinhoId) {

        List<Aluguel> aluguel = aluguelService.create(carrinhoId);


        return ResponseEntity.ok(aluguel);
    }
}
