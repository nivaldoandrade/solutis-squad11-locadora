package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.entities.ModeloCarro;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;


@Builder
public record CarroDTO(
        Long id,

        String chassi,

        String placa,

        BigDecimal valorDiaria,

        ModeloDTO modelo,

        List<AcessorioDTO> acessorios
) {

    public static CarroDTO from(Carro carro) {
        List<AcessorioDTO> acessorioDTOS = carro.getAcessorios().stream()
                .map(AcessorioDTO::from).toList();

        return CarroDTO.builder()
                .id(carro.getId())
                .chassi(carro.getChassi())
                .placa(carro.getPlaca())
                .valorDiaria(carro.getValorDiaria())
                .modelo(ModeloDTO.from(carro.getModelo()))
                .acessorios(acessorioDTOS)
                .build();
    }
}