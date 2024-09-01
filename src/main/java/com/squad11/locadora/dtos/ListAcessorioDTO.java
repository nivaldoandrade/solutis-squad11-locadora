package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Acessorio;
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
