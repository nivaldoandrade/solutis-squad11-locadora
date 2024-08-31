package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.Apolice;
import com.squad11.locadora.exceptions.PolicyNotFoundException;
import com.squad11.locadora.repositories.ApoliceRepository;
import com.squad11.locadora.services.ApoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ApoliceServiceImpl implements ApoliceService {

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Override
    public Apolice findById(Long apoliceId) {
        return apoliceRepository.findById(apoliceId)
                .orElseThrow(PolicyNotFoundException::new);
    }
}
