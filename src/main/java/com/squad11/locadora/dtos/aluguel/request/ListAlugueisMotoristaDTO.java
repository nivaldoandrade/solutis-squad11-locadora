package com.squad11.locadora.dtos.aluguel.request;

import com.squad11.locadora.dtos.aluguel.AluguelMotoristaDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListAlugueisMotoristaDTO(
        List<AluguelMotoristaDTO> alugueis,
        Long totalItems,
        Integer totalPages
) {

    public static ListAlugueisMotoristaDTO from(Page<Aluguel> pageAluguel) {
        List<AluguelMotoristaDTO> aluguelMotoristaDTOS = pageAluguel.stream()
                .map(AluguelMotoristaDTO::from).toList();

        return new ListAlugueisMotoristaDTO(
                aluguelMotoristaDTOS,
                pageAluguel.getTotalElements(),
                pageAluguel.getTotalPages()
        );
    }
}
