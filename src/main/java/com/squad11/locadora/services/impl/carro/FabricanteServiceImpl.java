package com.squad11.locadora.services.impl.carro;

import com.squad11.locadora.dtos.carro.request.CreateFabricanteDTO;
import com.squad11.locadora.entities.carro.Fabricante;
import com.squad11.locadora.exceptions.MakerNotFoundException;
import com.squad11.locadora.repositories.carro.FabricanteRepository;
import com.squad11.locadora.services.carro.FabricanteService;
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
