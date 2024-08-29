package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.CadastroPendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadastroPendenteRepository extends JpaRepository<CadastroPendente, Long> {

    Optional<CadastroPendente> findByToken(String token);

}
