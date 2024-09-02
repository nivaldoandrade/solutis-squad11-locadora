package com.squad11.locadora.dtos.carro.request;

import com.squad11.locadora.dtos.carro.CarroDTO;
import com.squad11.locadora.entities.carro.Carro;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListCarroDTO(
        List<CarroDTO> carros,
        Long totalItems,
        Integer totalPages
) {

    public static ListCarroDTO from(Page<Carro> pageCarro) {
        List<CarroDTO> carroDTOS = pageCarro.stream().map(CarroDTO::from).toList();

        return new ListCarroDTO(
                carroDTOS,
                pageCarro.getTotalElements(),
                pageCarro.getTotalPages()
        );
    }
}
