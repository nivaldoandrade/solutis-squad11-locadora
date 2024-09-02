package com.squad11.locadora.repositories.aluguel;

import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.pessoa.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

   Page<Pedido> findAllByMotorista(Pageable pageable, Motorista motorista);
}
