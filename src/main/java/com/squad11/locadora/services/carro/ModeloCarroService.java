package com.squad11.locadora.services.carro;

import com.squad11.locadora.dtos.carro.request.CreateModeloCarroDTO;
import com.squad11.locadora.entities.carro.ModeloCarro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ModeloCarroService {

    Page<ModeloCarro> list(Pageable pageable);

    ModeloCarro show(Long modeloCarroId);

    ModeloCarro create(CreateModeloCarroDTO createModeloCarroDTO);
}
