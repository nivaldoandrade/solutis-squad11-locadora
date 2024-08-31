package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.entities.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}
