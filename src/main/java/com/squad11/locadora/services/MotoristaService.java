package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateMotoristaDTO;
import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.entities.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MotoristaService {

    Motorista show(Long motoristaId);

    Aluguel showAluguel(Long motoristaId, Long aluguelId);

    Page<Aluguel> listAlugueis(Pageable pageable, Long motoristaId);

    Motorista findByIdAtivo(Long motoristaId);

    String create(CreateMotoristaDTO createMotoristaDTO);

    Motorista findById(Long motoristaId);
}
