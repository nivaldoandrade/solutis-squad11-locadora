package com.squad11.locadora.dtos.carro.request;

import com.squad11.locadora.dtos.carro.AcessorioDTO;
import com.squad11.locadora.entities.carro.Acessorio;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListAcessorioDTO(
        List<AcessorioDTO> acessorios,
        Long totalItems,
        Integer totalPages
) {
    public static ListAcessorioDTO from(Page<Acessorio> pageAcessorio) {
        List<AcessorioDTO> acessorioDTOS = pageAcessorio.stream().map(AcessorioDTO::from).toList();

        return new ListAcessorioDTO(
                acessorioDTOS,
                pageAcessorio.getTotalElements(),
                pageAcessorio.getTotalPages()
        );
    }

}
