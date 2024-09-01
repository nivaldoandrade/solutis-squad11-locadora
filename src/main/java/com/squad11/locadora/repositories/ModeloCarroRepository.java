package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.ModeloCarro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {
}
