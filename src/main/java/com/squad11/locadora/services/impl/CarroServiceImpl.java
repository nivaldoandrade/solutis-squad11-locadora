package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.exceptions.EntityNotFoundException;
import com.squad11.locadora.repositories.CarroRepository;
import com.squad11.locadora.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    @Override
    public Carro findById(Long id) {
        return carroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Carro.class));
    }
}
