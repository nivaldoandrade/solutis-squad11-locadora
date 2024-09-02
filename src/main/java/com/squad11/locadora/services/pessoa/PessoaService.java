package com.squad11.locadora.services.pessoa;

import com.squad11.locadora.entities.pessoa.Pessoa;

public interface PessoaService {

    void updateAtivo(Long id);

    Pessoa findById(Long id);

    void validateEmailUniqueness(String email, Long existingPessoaId);

    void validateCPFUniqueness(String cpf, Long existingMotoristaId);
}
