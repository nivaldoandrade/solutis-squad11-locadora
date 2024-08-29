package com.squad11.locadora.controllers;

import com.squad11.locadora.services.CadastroPendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
