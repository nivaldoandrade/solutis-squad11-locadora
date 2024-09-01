package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateCarroDTO;
import com.squad11.locadora.entities.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarroService {

    Page<Carro> list(Pageable pageable, List<String> categorias, List<String> acessorios);

    Carro findById(Long id);

    Carro findByIdDisponivel(Long id);

    void checkCarroDisponivel(Long carroId);

    Carro create(CreateCarroDTO createCarroDTO);
}
