package com.squad11.locadora.repositories.aluguel;

import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.pessoa.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    Page<Aluguel> findAllByMotorista(Pageable pageable, Motorista motorista);

    Optional<Aluguel> findByMotorista(Motorista motorista);
}
