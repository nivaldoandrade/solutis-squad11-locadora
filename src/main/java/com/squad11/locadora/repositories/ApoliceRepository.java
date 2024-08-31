package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.entities.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {
}
