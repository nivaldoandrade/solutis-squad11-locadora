package com.squad11.locadora.controllers.impl.aluguel;

import com.squad11.locadora.controllers.aluguel.AluguelController;
import com.squad11.locadora.dtos.aluguel.AluguelDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.services.aluguel.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AluguelControllerImpl implements AluguelController {

    @Autowired
    AluguelService aluguelService;

    @Override
    public ResponseEntity<AluguelDTO> show(@PathVariable Long aluguelId) {
        Aluguel aluguel = aluguelService.show(aluguelId);

        AluguelDTO aluguelDTO = AluguelDTO.from(aluguel);

        return ResponseEntity.ok().body(aluguelDTO);
    }
}
