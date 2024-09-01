package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateFabricanteDTO;
import com.squad11.locadora.entities.Fabricante;
import com.squad11.locadora.exceptions.MakerNotFoundException;
import com.squad11.locadora.repositories.FabricanteRepository;
import com.squad11.locadora.services.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FabricanteServiceImpl implements FabricanteService {

    @Autowired
    FabricanteRepository fabricanteRepository;

    @Override
    public Page<Fabricante> list(Pageable pageable) {
        return fabricanteRepository.findAll(pageable);
    }

    @Override
    public Fabricante show(Long fabricanteId) {
        return this.findById(fabricanteId);
    }

    @Override
    public Fabricante create(CreateFabricanteDTO createFabricanteDTO) {

        Fabricante fabricante = CreateFabricanteDTO.to(createFabricanteDTO);

        return fabricanteRepository.save(fabricante);
    }

    private Fabricante findById(Long fabricanteId) {
        return fabricanteRepository.findById(fabricanteId)
                .orElseThrow(MakerNotFoundException::new);
    }
}
