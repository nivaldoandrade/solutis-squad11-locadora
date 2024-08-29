package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.ModeloCarro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {
}
