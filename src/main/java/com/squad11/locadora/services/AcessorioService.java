package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateAcessorioDTO;
import com.squad11.locadora.entities.Acessorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcessorioService {

    Page<Acessorio> list(Pageable pageable);

    Acessorio show(Long acessorioId);

    Acessorio create(CreateAcessorioDTO createAcessorioDTO);
}
