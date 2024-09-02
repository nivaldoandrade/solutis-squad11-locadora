package com.squad11.locadora.dtos.pessoa.request;

import java.net.URI;

public record CreateLinkMotoristaDTO(
        URI linkConfirmacao
) {


    public static CreateLinkMotoristaDTO from(URI linkConfirmacaoURI) {
        return new CreateLinkMotoristaDTO(
                linkConfirmacaoURI
        );
    }
}
