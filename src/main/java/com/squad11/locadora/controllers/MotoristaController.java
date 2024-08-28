package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CreateMotoristaDTO;

import com.squad11.locadora.dtos.MotoristaDTO;
import com.squad11.locadora.entities.Motorista;
import com.squad11.locadora.services.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping
    public ResponseEntity<MotoristaDTO> create(@RequestBody @Validated CreateMotoristaDTO createMotoristaDTO) {

        Motorista motorista = motoristaService.create(createMotoristaDTO);

        MotoristaDTO motoristaDTO = MotoristaDTO.from(motorista);

        return ResponseEntity.ok().body(motoristaDTO);
    }
}
