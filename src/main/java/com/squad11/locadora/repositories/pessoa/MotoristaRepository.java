package com.squad11.locadora.repositories.pessoa;

import com.squad11.locadora.entities.pessoa.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    Optional<Motorista> findByNumeroCNH(String numeroCNH);

}
