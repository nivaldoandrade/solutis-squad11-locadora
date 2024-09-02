package com.squad11.locadora.services.aluguel;

import com.squad11.locadora.dtos.aluguel.request.CreateApoliceSeguroDTO;
import com.squad11.locadora.entities.aluguel.Apolice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApoliceSeguroService {

    Page<Apolice> list(Pageable pageable);

    Apolice show(Long apoliceSeguroId);

    Apolice create(CreateApoliceSeguroDTO createApoliceSeguroDTO);
}
