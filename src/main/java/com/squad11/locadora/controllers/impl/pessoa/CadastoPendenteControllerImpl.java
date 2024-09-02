package com.squad11.locadora.controllers.impl.pessoa;

import com.squad11.locadora.controllers.pessoa.CadastoPendenteController;
import com.squad11.locadora.dtos.pessoa.request.CreateLinkMotoristaDTO;
import com.squad11.locadora.services.pessoa.CadastroPendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CadastoPendenteControllerImpl implements CadastoPendenteController {

    @Autowired
    private CadastroPendenteService cadastroPendenteService;

    @Override
    public ResponseEntity<String> validTokenConfirmation(@PathVariable String token) {

        cadastroPendenteService.confirmToken(token);

        return ResponseEntity.ok().body("Obrigado por confirmar o seu cadastro!");
    }

    @Override
    public ResponseEntity<CreateLinkMotoristaDTO> createLinkConfirmation(
            @PathVariable Long motoristaId
    ) {
        String token = cadastroPendenteService.createToken(motoristaId);

        URI linkConfirmacaoURI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/confirmar-cadastro/")
                .path(token)
                .build().toUri();

        CreateLinkMotoristaDTO createMotoristaDTO = CreateLinkMotoristaDTO.from(linkConfirmacaoURI);

        return ResponseEntity.created(linkConfirmacaoURI).body(createMotoristaDTO);
    }
}
