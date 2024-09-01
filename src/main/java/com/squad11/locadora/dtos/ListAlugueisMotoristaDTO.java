package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Aluguel;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListAlugueisMotoristaDTO(
        List<ResponseAluguelMotoristaDTO> alugueis,
        Long totalItems,
        Integer totalPages
) {

    public static ListAlugueisMotoristaDTO from(Page<Aluguel> pageAluguel) {
        List<ResponseAluguelMotoristaDTO> aluguelMotoristaDTOS = pageAluguel.stream()
                .map(ResponseAluguelMotoristaDTO::from).toList();

        return new ListAlugueisMotoristaDTO(
                aluguelMotoristaDTOS,
                pageAluguel.getTotalElements(),
                pageAluguel.getTotalPages()
        );
    }
}
