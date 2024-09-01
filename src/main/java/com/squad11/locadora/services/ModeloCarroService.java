package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateModeloCarroDTO;
import com.squad11.locadora.entities.ModeloCarro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ModeloCarroService {

    Page<ModeloCarro> list(Pageable pageable);

    ModeloCarro show(Long modeloCarroId);

    ModeloCarro create(CreateModeloCarroDTO createModeloCarroDTO);
}
