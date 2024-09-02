package com.squad11.locadora.services.impl.aluguel;

import com.squad11.locadora.dtos.aluguel.request.CreateApoliceSeguroDTO;
import com.squad11.locadora.entities.aluguel.Apolice;
import com.squad11.locadora.exceptions.PolicyNotFoundException;
import com.squad11.locadora.repositories.aluguel.ApoliceRepository;
import com.squad11.locadora.services.aluguel.ApoliceSeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ApoliceSeguroImpl implements ApoliceSeguroService {

    @Autowired
    ApoliceRepository apoliceRepository;

    @Override
    public Page<Apolice> list(Pageable pageable) {
        return apoliceRepository.findAll(pageable);
    }

    @Override
    public Apolice show(Long apoliceSeguroId) {
        return this.findById(apoliceSeguroId);
    }

    @Override
    public Apolice create(CreateApoliceSeguroDTO createApoliceSeguroDTO) {
        Apolice apolice = CreateApoliceSeguroDTO.to(createApoliceSeguroDTO);

        return apoliceRepository.save(apolice);
    }

    private Apolice findById(Long apoliceSeguroId) {
        return apoliceRepository.findById(apoliceSeguroId)
                .orElseThrow(PolicyNotFoundException::new);
    }
}
