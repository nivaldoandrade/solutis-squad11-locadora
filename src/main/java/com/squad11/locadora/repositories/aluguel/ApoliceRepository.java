package com.squad11.locadora.repositories.aluguel;

import com.squad11.locadora.entities.aluguel.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {
}