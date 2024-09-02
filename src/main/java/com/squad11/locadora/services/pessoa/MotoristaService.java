package com.squad11.locadora.services.pessoa;

import com.squad11.locadora.dtos.pessoa.request.CreateMotoristaDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.pessoa.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MotoristaService {

    Motorista show(Long motoristaId);

    Page<Aluguel> listAlugueis(Pageable pageable, Long motoristaId);

    Page<Pedido> listPedidos(Pageable pageable, Long motoristaId);

    Motorista findByIdAtivo(Long motoristaId);

    String create(CreateMotoristaDTO createMotoristaDTO);

    Motorista findById(Long motoristaId);
}
