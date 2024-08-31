package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.entities.Aluguel;
import com.squad11.locadora.entities.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    List<Aluguel> findAllByMotorista(Motorista motorista);
}
