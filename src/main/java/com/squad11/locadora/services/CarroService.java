package com.squad11.locadora.services;

import com.squad11.locadora.entities.Carro;

import java.util.List;

public interface CarroService {
    List<Carro> findAll();

    Carro findById(Long id);
}
