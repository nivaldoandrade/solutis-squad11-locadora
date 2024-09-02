package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.entities.aluguel.Apolice;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ApoliceDTO(
        Long id,

        BigDecimal valorFranquia,

        Boolean protecaoTerceiro,

        Boolean protecaoCausasNaturais,

        Boolean protecaoRoubo
) {

    public static ApoliceDTO from(Apolice apolice) {
        return ApoliceDTO.builder()
                .id(apolice.getId())
                .valorFranquia(apolice.getValorFranquia())
                .protecaoTerceiro(apolice.getProtecaoTerceiro())
                .protecaoCausasNaturais(apolice.getProtecaoCausasNaturais())
                .protecaoRoubo(apolice.getProtecaoRoubo())
                .build();
    }
}
