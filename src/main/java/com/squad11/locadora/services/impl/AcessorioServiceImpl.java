package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateAcessorioDTO;
import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.exceptions.AccessoryNotFoundException;
import com.squad11.locadora.repositories.AcessorioRepository;
import com.squad11.locadora.services.AcessorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AcessorioServiceImpl implements AcessorioService {

    @Autowired
    AcessorioRepository acessorioRepository;

    @Override
    public Page<Acessorio> list(Pageable pageable) {
        return acessorioRepository.findAll(pageable);
    }

    @Override
    public Acessorio show(Long acessorioId) {
        return this.findById(acessorioId);
    }

    @Override
    public Acessorio create(CreateAcessorioDTO createAcessorioDTO) {
        Acessorio acessorio = CreateAcessorioDTO.to(createAcessorioDTO);

        return acessorioRepository.save(acessorio);
    }

    private Acessorio findById(Long acessorioId) {
        return acessorioRepository.findById(acessorioId)
                .orElseThrow(AccessoryNotFoundException::new);
    }

}