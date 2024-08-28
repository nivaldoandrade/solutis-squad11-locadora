package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    Optional<Motorista> findByEmail(String email);
}
