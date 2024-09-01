package com.squad11.locadora.dtos;

import java.net.URI;

public record ResponseCreateMotoristaDTO(
        URI linkConfirmacao
) {


    public static ResponseCreateMotoristaDTO from(URI linkConfirmacaoURI) {
        return new ResponseCreateMotoristaDTO(
                linkConfirmacaoURI
        );
    }
}
