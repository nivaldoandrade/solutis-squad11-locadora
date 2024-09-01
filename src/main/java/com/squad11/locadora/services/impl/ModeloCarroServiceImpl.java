package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateModeloCarroDTO;
import com.squad11.locadora.entities.Fabricante;
import com.squad11.locadora.entities.ModeloCarro;
import com.squad11.locadora.exceptions.CarModelNotFoundException;
import com.squad11.locadora.repositories.ModeloCarroRepository;
import com.squad11.locadora.services.FabricanteService;
import com.squad11.locadora.services.ModeloCarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ModeloCarroServiceImpl implements ModeloCarroService {

    @Autowired
    ModeloCarroRepository modeloCarroRepository;

    @Autowired
    FabricanteService fabricanteService;

    @Override
    public Page<ModeloCarro> list(Pageable pageable) {
        return modeloCarroRepository.findAll(pageable);
    }

    @Override
    public ModeloCarro show(Long modeloCarroId) {
        return this.findById(modeloCarroId);
    }

    @Override
    public ModeloCarro create(CreateModeloCarroDTO createModeloCarroDTO) {
        Fabricante fabricante = fabricanteService.show(createModeloCarroDTO.fabricanteId());

        ModeloCarro modeloCarro = CreateModeloCarroDTO.to(createModeloCarroDTO, fabricante);

        return modeloCarroRepository.save(modeloCarro);
    }

    private ModeloCarro findById(Long modeloCarroId) {
        return modeloCarroRepository.findById(modeloCarroId)
                .orElseThrow(CarModelNotFoundException::new);
    }
}
