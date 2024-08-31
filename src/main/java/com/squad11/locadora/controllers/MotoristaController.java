package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CreateMotoristaDTO;

import com.squad11.locadora.dtos.ResponseMotoristaDTO;
import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.services.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @GetMapping("{motoristaId}/alugueis")
    public ResponseEntity<?> showAlugueis(@PathVariable Long motoristaId) {

        List<Aluguel> alugueis = motoristaService.showAlugueis(motoristaId);

        return ResponseEntity.ok(alugueis);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMotoristaDTO> create(@RequestBody CreateMotoristaDTO createMotoristaDTO) {

        String token = motoristaService.create(createMotoristaDTO);

        URI linkConfirmacaoURI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/confirmar-cadastro/")
                .path(token)
                .build().toUri();

        ResponseMotoristaDTO motoristaDTO = ResponseMotoristaDTO.from(linkConfirmacaoURI);

        return ResponseEntity.created(linkConfirmacaoURI).body(motoristaDTO);
    }
}
