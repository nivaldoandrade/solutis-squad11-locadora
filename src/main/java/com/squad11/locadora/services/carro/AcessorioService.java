package com.squad11.locadora.services.carro;

import com.squad11.locadora.dtos.carro.request.CreateAcessorioDTO;
import com.squad11.locadora.entities.carro.Acessorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcessorioService {

    Page<Acessorio> list(Pageable pageable);

    Acessorio show(Long acessorioId);

    Acessorio create(CreateAcessorioDTO createAcessorioDTO);
}
