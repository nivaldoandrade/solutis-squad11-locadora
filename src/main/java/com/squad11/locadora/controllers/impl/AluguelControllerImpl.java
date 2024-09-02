package com.squad11.locadora.controllers.impl;

import com.squad11.locadora.controllers.AluguelController;
import com.squad11.locadora.dtos.ResponseAluguelDTO;
import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.services.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AluguelControllerImpl implements AluguelController {

    @Autowired
    AluguelService aluguelService;

    @Override
    public ResponseEntity<ResponseAluguelDTO> show(@PathVariable Long aluguelId) {
        Aluguel aluguel = aluguelService.show(aluguelId);

        ResponseAluguelDTO responseAluguelDTO = ResponseAluguelDTO.from(aluguel);

        return ResponseEntity.ok().body(responseAluguelDTO);
    }
}
