package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarroRepository extends JpaRepository<Carro, Long>, JpaSpecificationExecutor<Carro> {
}