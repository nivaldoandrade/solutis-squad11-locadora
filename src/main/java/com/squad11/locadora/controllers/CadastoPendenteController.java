package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.ResponseCreateMotoristaDTO;
import com.squad11.locadora.services.CadastroPendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/confirmar-cadastro")
public class CadastoPendenteController {

    @Autowired
    private CadastroPendenteService cadastroPendenteService;

    @GetMapping("{token}")
    public ResponseEntity<String> validTokenConfirmation(@PathVariable String token) {

        cadastroPendenteService.confirmToken(token);

        return ResponseEntity.ok().body("Obrigado por confirmar o seu cadastro!");
    }

    @PostMapping("{motoristaId}")
    public ResponseEntity<ResponseCreateMotoristaDTO> createLinkConfirmation(
            @PathVariable Long motoristaId
    ) {
        String token = cadastroPendenteService.createToken(motoristaId);

        URI linkConfirmacaoURI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/confirmar-cadastro/")
                .path(token)
                .build().toUri();

        ResponseCreateMotoristaDTO createMotoristaDTO = ResponseCreateMotoristaDTO.from(linkConfirmacaoURI);

        return ResponseEntity.created(linkConfirmacaoURI).body(createMotoristaDTO);
    }
}
