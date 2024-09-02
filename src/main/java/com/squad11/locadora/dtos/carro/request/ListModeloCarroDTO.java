package com.squad11.locadora.dtos.carro.request;

import com.squad11.locadora.dtos.carro.ModeloDTO;
import com.squad11.locadora.entities.carro.ModeloCarro;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListModeloCarroDTO(
        List<ModeloDTO> modelosCarros,
        Long totalItems,
        Integer totalPages
) {
    public static ListModeloCarroDTO from(Page<ModeloCarro> pageModeloCarro) {
        List<ModeloDTO> modeloDTOS = pageModeloCarro.stream().map(ModeloDTO::from).toList();

        return new ListModeloCarroDTO(
                modeloDTOS,
                pageModeloCarro.getTotalElements(),
                pageModeloCarro.getTotalPages()
        );
    }

}
