package com.squad11.locadora.services.carro;

import com.squad11.locadora.dtos.carro.request.CreateCarroDTO;
import com.squad11.locadora.entities.carro.Carro;
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
