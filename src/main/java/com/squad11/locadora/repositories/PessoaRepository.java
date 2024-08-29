package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByCpf(String cpf);

}
