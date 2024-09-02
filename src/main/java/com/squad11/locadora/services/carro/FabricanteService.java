package com.squad11.locadora.services.carro;

import com.squad11.locadora.dtos.carro.request.CreateFabricanteDTO;
import com.squad11.locadora.entities.carro.Fabricante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FabricanteService {

    Page<Fabricante> list(Pageable pageable);

    Fabricante show(Long fabricanteId);

    Fabricante create(CreateFabricanteDTO createFabricanteDTO);
}
