package com.squad11.locadora.services;

import com.squad11.locadora.entities.Aluguel;

import java.util.List;

public interface AluguelService {

    List<Aluguel> create(Long carrinhoId);

    void payment(Long aluguelId);
}
