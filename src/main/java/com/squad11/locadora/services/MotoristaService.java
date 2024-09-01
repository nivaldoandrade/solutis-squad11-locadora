package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateMotoristaDTO;
import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.entities.Motorista;

import java.util.List;

public interface MotoristaService {

    List<Aluguel>  showAlugueis(Long motoristaId);

    Motorista findByIdAtivo(Long motoristaId);

    String create(CreateMotoristaDTO createMotoristaDTO);

    Motorista findById(Long motoristaId);
}
