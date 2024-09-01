package com.squad11.locadora.services;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.entities.CategoriaEnum;
import com.squad11.locadora.entities.StatusCarroEnum;
import com.squad11.locadora.exceptions.CarNotAvailableForRentalException;

import java.util.List;

public interface CarroService {
    List<Carro> findAll(List<String> categorias, List<String> acessorios);

    Carro findById(Long id);

    Carro findByIdDisponivel(Long id);

    void checkCarroDisponivel(Long carroId);
}
