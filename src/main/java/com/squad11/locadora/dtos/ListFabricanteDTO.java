package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Fabricante;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListFabricanteDTO(
        List<FabricanteDTO> fabricantes,
        Long totalItems,
        Integer totalPages
) {
    public static ListFabricanteDTO from(Page<Fabricante> pageFabricante) {
        List<FabricanteDTO> fabricanteDTOS = pageFabricante.stream().map(FabricanteDTO::from).toList();

        return new ListFabricanteDTO(
                fabricanteDTOS,
                pageFabricante.getTotalElements(),
                pageFabricante.getTotalPages()
        );
    }

}
