package com.squad11.locadora.dtos.carro;

import com.squad11.locadora.entities.carro.Carro;
import com.squad11.locadora.entities.enums.StatusCarroEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CarroDTO(
        Long id,

        String chassi,

        String placa,

        String cor,

        StatusCarroEnum status,

        BigDecimal valorDiaria,

        String foto,

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
                .cor(carro.getCor())
                .status(carro.getStatus())
                .valorDiaria(carro.getValorDiaria())
                .foto(carro.getFoto())
                .modelo(ModeloDTO.from(carro.getModelo()))
                .acessorios(acessorioDTOS)
                .build();
    }
}
