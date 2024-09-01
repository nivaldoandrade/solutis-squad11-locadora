package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateAluguelDTO;
import com.squad11.locadora.entities.Aluguel;

import java.util.List;

public interface AluguelService {

    Aluguel show(Long aluguelId);

    List<Aluguel> create(Long carrinhoId, CreateAluguelDTO createAluguelDTO);
}
