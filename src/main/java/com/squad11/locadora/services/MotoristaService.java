package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateMotoristaDTO;
import com.squad11.locadora.entities.Motorista;

public interface MotoristaService {

    Motorista create(CreateMotoristaDTO createMotoristaDTO);
}
