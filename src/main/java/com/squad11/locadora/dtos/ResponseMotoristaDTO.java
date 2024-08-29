package com.squad11.locadora.dtos;

import java.net.URI;

public record ResponseMotoristaDTO(
        URI linkConfirmacao
) {


    public static ResponseMotoristaDTO from(URI linkConfirmacaoURI) {
        return new ResponseMotoristaDTO(
                linkConfirmacaoURI
        );
    }
}
