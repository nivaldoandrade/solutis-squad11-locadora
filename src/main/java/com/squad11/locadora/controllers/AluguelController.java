package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CreateAluguelDTO;
import com.squad11.locadora.dtos.ResponseAluguelDTO;
import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.services.AluguelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    @Autowired
    AluguelService aluguelService;

    @GetMapping("{aluguelId}")
    public ResponseEntity<ResponseAluguelDTO> show(@PathVariable Long aluguelId) {
        Aluguel aluguel = aluguelService.show(aluguelId);

        ResponseAluguelDTO responseAluguelDTO = ResponseAluguelDTO.from(aluguel);

        return ResponseEntity.ok().body(responseAluguelDTO);
    }
}
