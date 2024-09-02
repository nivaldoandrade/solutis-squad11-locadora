package com.squad11.locadora.services.aluguel;

import com.squad11.locadora.dtos.aluguel.request.CreateAluguelDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.pessoa.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AluguelService {

    Aluguel show(Long aluguelId);

    List<Aluguel> create(Long carrinhoId, CreateAluguelDTO createAluguelDTO);
}
