package com.squad11.locadora.repositories.aluguel;

import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.pessoa.Motorista;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByMotorista(Motorista motorista);
}
