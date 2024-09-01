package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Apolice;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListApoliceSeguroDTO(
        List<ApoliceDTO> apolices,
        Long totalItems,
        Integer totalPages
) {
    public static ListApoliceSeguroDTO from(Page<Apolice> pageApolice) {
        List<ApoliceDTO> apoliceDTOS = pageApolice.stream().map(ApoliceDTO::from).toList();

        return new ListApoliceSeguroDTO(
                apoliceDTOS,
                pageApolice.getTotalElements(),
                pageApolice.getTotalPages()
        );
    }

}
