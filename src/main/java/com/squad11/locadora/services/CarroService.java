package com.squad11.locadora.services;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.entities.CategoriaEnum;

import java.util.List;

public interface CarroService {
    List<Carro> findAll(List<String> categorias, List<String> acessorios);

    Carro findById(Long id);
}
