package com.squad11.locadora.services;

import com.squad11.locadora.entities.Pessoa;

public interface PessoaService {

    void updateAtivo(Long id);

    Pessoa findById(Long id);

    void validateEmailUniqueness(String email, Long existingPessoaId);

    void validateCPFUniqueness(String cpf, Long existingMotoristaId);
}
