package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Acessorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {
}
