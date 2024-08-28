package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}